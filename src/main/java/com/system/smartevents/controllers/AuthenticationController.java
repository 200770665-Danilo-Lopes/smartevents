package com.system.smartevents.controllers;

import com.system.smartevents.dtos.AuthenticationDto;
import com.system.smartevents.dtos.LoginResponseDto;
import com.system.smartevents.dtos.UsuarioDadosDto;
import com.system.smartevents.dtos.UsuarioDto;
import com.system.smartevents.infra.security.TokenService;
import com.system.smartevents.models.EventoModel;
import com.system.smartevents.models.UsuarioModel;
import com.system.smartevents.repositories.UserRepository;
import com.system.smartevents.services.DadosUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    private DadosUserService dadosUserService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.login(),authenticationDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioModel) auth.getPrincipal());


            return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public  ResponseEntity register(@RequestBody @Valid UsuarioDto usuarioDto){
        if(this.userRepository.findByLogin(usuarioDto.login()) != null) return ResponseEntity.badRequest().build();

        String encryotedPassword = new BCryptPasswordEncoder().encode(usuarioDto.password());
        UsuarioModel newUser = new UsuarioModel(usuarioDto.login(),encryotedPassword,usuarioDto.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

//
//    @GetMapping("/login/acess")
//    public List<UsuarioModel> obterDados() {
//        return dadosUserService.obterDados();
//    }


    @PostMapping("/login/acess")
    public ResponseEntity loginTeste(@RequestBody @Valid UsuarioDadosDto usuarioDadosDto){
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDadosDto, usuarioModel);


        return ResponseEntity.ok(userRepository.findByLogin(usuarioModel.getLogin()));
    }




}

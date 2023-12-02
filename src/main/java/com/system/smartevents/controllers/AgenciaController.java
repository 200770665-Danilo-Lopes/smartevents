
package com.system.smartevents.controllers;

import com.system.smartevents.dtos.AgenciaDto;
import com.system.smartevents.models.AgenciaModel;
import com.system.smartevents.repositories.AgenciaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AgenciaController {

    @Autowired
    AgenciaRepository agenciaRepository;


    @PostMapping("/agencias")
    public ResponseEntity<AgenciaModel> saveAgencia(@RequestBody @Valid AgenciaDto agenciaDto) {
        var agenciaModel= new AgenciaModel();
        BeanUtils.copyProperties(agenciaDto, agenciaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(agenciaRepository.save(agenciaModel));
    }

    @GetMapping("/agencias")
    public ResponseEntity<List<AgenciaModel>> getAllAgencias(){
        List<AgenciaModel> agenciaList = agenciaRepository.findAll();
        if(!agenciaList.isEmpty()) {
            for(AgenciaModel agencia : agenciaList) {
            	Integer id = agencia.getIdAgencia();
                agencia.add(linkTo(methodOn(AgenciaController.class).getOneAgencia(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(agenciaList);
    }



    @GetMapping("/agencias/{id}")
    public ResponseEntity<Object> getOneAgencia(@PathVariable(value="id") Integer id){
        Optional<AgenciaModel> agenciaO = agenciaRepository.findById(id);
        if(agenciaO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("agencia not found.");
        }
        agenciaO.get().add(linkTo(methodOn(AgenciaController.class).getAllAgencias()).withRel("Agencia List"));
        return ResponseEntity.status(HttpStatus.OK).body(agenciaO.get());
    }


    @PutMapping("/agencias/{id}")
    public ResponseEntity<Object> updateAgencia(@PathVariable(value="id") Integer id,
                                                @RequestBody @Valid AgenciaDto agenciaDto) {
        Optional<AgenciaModel> agenciaO = agenciaRepository.findById(id);
        if(agenciaO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agencia not found.");
        }
        AgenciaModel agenciaModel = agenciaO.get();
        BeanUtils.copyProperties(agenciaDto, agenciaModel);
        return ResponseEntity.status(HttpStatus.OK).body(agenciaRepository.save(agenciaModel));
    }




    @DeleteMapping("/agencias/{id}")
    public ResponseEntity<Object> deleteAgencia(@PathVariable(value="id") Integer id) {
        Optional<AgenciaModel> agenciaO = agenciaRepository.findById(id);
        if(agenciaO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agencia not found.");
        }
        agenciaRepository.delete(agenciaO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Agencia deleted successfully.");
    }



}

package com.system.smartevents.controllers;


import com.system.smartevents.dtos.AgendamentoDto;

import com.system.smartevents.models.AgendamentoModel;

import com.system.smartevents.repositories.AgendamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AgendamentoController {

    @Autowired
    AgendamentoRepository agendamentoRepository;


    @PostMapping("/agendamentos")
    public ResponseEntity<AgendamentoModel> saveAgendamento(@RequestBody @Valid AgendamentoDto agendamentoDto) {
        var agendamentoModel= new AgendamentoModel();
        BeanUtils.copyProperties(agendamentoDto, agendamentoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoRepository.save(agendamentoModel));
    }

    @GetMapping("/agendamentos")
    public ResponseEntity<List<AgendamentoModel>> getAllAgendamento(){
        List<AgendamentoModel> agendaList = agendamentoRepository.findAll();
        if(!agendaList.isEmpty()) {
            for(AgendamentoModel agenda : agendaList) {
                int id = agenda.getIdAgendamento();
                agenda.add(linkTo(methodOn(AgendamentoController.class).getOneAgendamento(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(agendaList);
    }



    @GetMapping("/agendamentos/{id}")
    public ResponseEntity<Object> getOneAgendamento(@PathVariable(value="id") int id){
        Optional<AgendamentoModel> agendaO = agendamentoRepository.findById(id);
        if(agendaO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento not found.");
        }
        agendaO.get().add(linkTo(methodOn(AgendamentoController.class).getAllAgendamento()).withRel("Agendamento List"));
        return ResponseEntity.status(HttpStatus.OK).body(agendaO.get());
    }
    
    

    @GetMapping("/agendamentos/evento/{id}")
    public ResponseEntity<Object> getOneAgendamentoEvento(@PathVariable(value="id") int id){
        Optional<AgendamentoModel> agendaO = agendamentoRepository.findById(id);
        if(agendaO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento not found.");
        }
        agendaO.get().add(linkTo(methodOn(AgendamentoController.class).getAllAgendamento()).withRel("Agendamento List"));
        return ResponseEntity.status(HttpStatus.OK).body(agendaO.get());
    }


    @PutMapping("/agendamentos/{id}")
    public ResponseEntity<Object> updateAgendamento(@PathVariable(value="id") int id,
                                                @RequestBody @Valid AgendamentoDto agendamentoDto) {
        Optional<AgendamentoModel> agendaO = agendamentoRepository.findById(id);
        if(agendaO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento not found.");
        }
        AgendamentoModel agendamentoModel = agendaO.get();
        BeanUtils.copyProperties(agendamentoDto, agendamentoModel);
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoRepository.save(agendamentoModel));
    }




    @DeleteMapping("/agendamentos/{id}")
    public ResponseEntity<Object> deleteAgendamento(@PathVariable(value="id") int id) {
        Optional<AgendamentoModel> agendaO = agendamentoRepository.findById(id);
        if(agendaO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento not found.");
        }
        agendamentoRepository.delete(agendaO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Agendamento deleted successfully.");
    }



}

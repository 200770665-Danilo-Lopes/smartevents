
package com.system.smartevents.controllers;


import com.system.smartevents.dtos.EventoDto;
import com.system.smartevents.models.EventoModel;
import com.system.smartevents.repositories.EventoRepository;
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
public class EventoController {

    @Autowired
    EventoRepository eventoRepository;


    @PostMapping("/eventos")
    public ResponseEntity<EventoModel> saveEvento(@RequestBody @Valid EventoDto eventoDto) {
        var eventoModel= new EventoModel();
        BeanUtils.copyProperties(eventoDto, eventoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoRepository.save(eventoModel));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<EventoModel>> getAllEvento(){
        List<EventoModel> eventoList = eventoRepository.findAll();
        if(!eventoList.isEmpty()) {
            for(EventoModel evento : eventoList) {
                int id = evento.getIdEvento();
                evento.add(linkTo(methodOn(EventoController.class).getOneEvento(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(eventoList);
    }



    @GetMapping("/eventos/{id}")
    public ResponseEntity<Object> getOneEvento(@PathVariable(value="id") int id){
        Optional<EventoModel> eventoO = eventoRepository.findById(id);
        if(eventoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento not found.");
        }
        eventoO.get().add(linkTo(methodOn(EventoController.class).getAllEvento()).withRel("Evento List"));
        return ResponseEntity.status(HttpStatus.OK).body(eventoO.get());
    }



    @GetMapping("/eventos/agendamentos/{id}")
    public ResponseEntity<Object> getOneEventoAgendamentos(@PathVariable(value="id") int id){
        Optional<EventoModel> eventoO = eventoRepository.findById(id);
        if(eventoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento not found.");
        }
        eventoO.get().add(linkTo(methodOn(EventoController.class).getAllEvento()).withRel("Evento List"));
        return ResponseEntity.status(HttpStatus.OK).body(eventoO.get());
    }


    @PutMapping("/eventos/{id}")
    public ResponseEntity<Object> updateEvento(@PathVariable(value="id") int id,
                                                @RequestBody @Valid EventoDto eventoDto) {
        Optional<EventoModel> eventoO = eventoRepository.findById(id);
        if(eventoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento not found.");
        }
        EventoModel eventoModel = eventoO.get();
        BeanUtils.copyProperties(eventoDto, eventoModel);
        return ResponseEntity.status(HttpStatus.OK).body(eventoRepository.save(eventoModel));
    }




    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Object> deleteEvento(@PathVariable(value="id") int id) {
        Optional<EventoModel> eventoO = eventoRepository.findById(id);
        if(eventoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento not found.");
        }
        eventoRepository.delete(eventoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Evento deleted successfully.");
    }



}

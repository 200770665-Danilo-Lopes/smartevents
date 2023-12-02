package com.system.smartevents.controllers;

import com.system.smartevents.dtos.MembroDto;
import com.system.smartevents.models.MembroModel;
import com.system.smartevents.repositories.MembroRepository;
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
public class MembroController {


    @Autowired
    MembroRepository membroRepository;

    @PostMapping("/membros")
    public ResponseEntity<MembroModel> saveMembro(@RequestBody @Valid MembroDto membroDto) {
        var membroModel= new MembroModel();
        BeanUtils.copyProperties(membroDto, membroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(membroRepository.save(membroModel));
    }

    @GetMapping("/membros")
    public ResponseEntity<List<MembroModel>> getAllMembros(){
        List<MembroModel> membroList = membroRepository.findAll();
        if(!membroList.isEmpty()) {
            for(MembroModel membro : membroList) {
                int id = membro.getIdMembro();
                membro.add(linkTo(methodOn(MembroController.class).getOneMembro(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(membroList);
    }



    @GetMapping("/membros/{id}")
    public ResponseEntity<Object> getOneMembro(@PathVariable(value="id") int id){
        Optional<MembroModel> membroO = membroRepository.findById(id);
        if(membroO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro not found.");
        }
        membroO.get().add(linkTo(methodOn(MembroController.class).getAllMembros()).withRel("Membro List"));
        return ResponseEntity.status(HttpStatus.OK).body(membroO.get());
    }


    @PutMapping("/membros/{id}")
    public ResponseEntity<Object> updateMembro(@PathVariable(value="id") int id,
                                                @RequestBody @Valid MembroDto membroDto) {
        Optional<MembroModel> membroO = membroRepository.findById(id);
        if(membroO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro not found.");
        }
        var membroModel = membroO.get();
        BeanUtils.copyProperties(membroDto, membroModel);
        return ResponseEntity.status(HttpStatus.OK).body(membroRepository.save(membroModel));
    }




    @DeleteMapping("/membros/{id}")
    public ResponseEntity<Object> deleteMembro(@PathVariable(value="id") int id) {
        Optional<MembroModel> membroO = membroRepository.findById(id);
        if(membroO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro not found.");
        }
        membroRepository.delete(membroO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Membro deleted successfully.");
    }


}
package com.curso.demo.controllers;

import com.curso.demo.model.domain.direccionDTO.DireccionAddDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionReadDTO;
import com.curso.demo.services.DireccionService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direccion")
public class DireccionController {
    
    private final DireccionService direccionserv;

    public DireccionController(DireccionService direccionserv) {
        this.direccionserv = direccionserv;
    }
    
    @GetMapping
    public ResponseEntity<List<DireccionReadDTO>> findAll(){
        return ResponseEntity.ok(direccionserv.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionReadDTO> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(direccionserv.findById(id));
    }
    
    //    @PostMapping
//    public ResponseEntity<DireccionReadDTO> add(@RequestBody DireccionAddDTO direccionAddDTO){
//        return ResponseEntity.ok(direccionserv.add(direccionAddDTO));
//    }
    
}

package com.primerdesafio.demo.controllers;

import com.primerdesafio.demo.model.domain.ContactoDTO;
import com.primerdesafio.demo.services.ContactoService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactoController {

    private final ContactoService contactoservice;

    public ContactoController(ContactoService contactoservice) {
        this.contactoservice = contactoservice;
    }

    // Consignas desafio 2
    
    @PostMapping("/contacto")
    public ResponseEntity<Map> save(@RequestBody ContactoDTO contactodto) {
        ContactoDTO nuevo = contactoservice.add(contactodto);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", nuevo.getId());
        map.put("mensaje", "añadido exitosamente");
        return ResponseEntity.ok(map);
    }
    
    @GetMapping("/contacto/{id}")
    public ResponseEntity<ContactoDTO> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoservice.findOne(id));
    }
    
    // Extras
    
    @PutMapping("/contacto/{id}")
    public ResponseEntity<Map> update(@PathVariable("id") Integer id, @RequestBody ContactoDTO contactodto) {
        ContactoDTO editable = contactoservice.findOne(id);
        editable.setNombre(contactodto.getNombre());
        editable.setCelular(contactodto.getCelular());
        contactoservice.add(editable);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", editable.getId());
        map.put("mensaje", "modificado exitosamente");
        return ResponseEntity.ok(map);
    }
    
    @DeleteMapping("/contacto/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        contactoservice.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

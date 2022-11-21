package com.primerdesafio.demo.controllers;

import com.primerdesafio.demo.model.domain.ContactoDTO;
import com.primerdesafio.demo.model.domain.MensajeDTO;
import com.primerdesafio.demo.services.ContactoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacto")
public class ContactoController {

    private final ContactoService contactoservice;

    public ContactoController(ContactoService contactoservice) {
        this.contactoservice = contactoservice;
    }

    @PostMapping
    public ResponseEntity<MensajeDTO> save(@RequestBody ContactoDTO contactodto) {
        return ResponseEntity.ok(contactoservice.add(contactodto));
    }

    @GetMapping
    public ResponseEntity<List<ContactoDTO>> getAll() {
        return ResponseEntity.ok(contactoservice.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoDTO> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoservice.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoservice.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody ContactoDTO contactodto) {
        return ResponseEntity.ok(contactoservice.update(id, contactodto));
    }

}

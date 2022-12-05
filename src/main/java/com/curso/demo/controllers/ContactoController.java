package com.curso.demo.controllers;

import com.curso.demo.model.domain.contactoDTO.ContactoAddDTO;
import com.curso.demo.model.domain.MensajeDTO;
import com.curso.demo.model.domain.contactoDTO.ContactoReadDTO;
import com.curso.demo.model.domain.contactoDTO.ListaContactosDTO;
import com.curso.demo.services.ContactoService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacto")
public class ContactoController {

    private final ContactoService contactoservice;

    public ContactoController(ContactoService contactoservice) {
        this.contactoservice = contactoservice;
    }

    @PostMapping
    public ResponseEntity<MensajeDTO> save(@RequestBody ContactoAddDTO contactodto) {
        return ResponseEntity.ok(contactoservice.add(contactodto));
    }

//    @GetMapping
//    public ResponseEntity<List<ContactoReadDTO>> getAll() {
//        return ResponseEntity.ok(contactoservice.findAll());
//    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ContactoReadDTO> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoservice.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoservice.delete(id));
    }
    
    @GetMapping
    public ResponseEntity<Page<ContactoReadDTO>> getAllWithPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "2") Integer size) {

        return ResponseEntity.ok(contactoservice.findAllWithPagitation(page, size));
    }
    
    @GetMapping("/filtros")
    public ResponseEntity<ListaContactosDTO> getAllWithFilters(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "2") Integer size,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String celular) {

        return ResponseEntity.ok(contactoservice.findAllWithFilters(page, size, nombre, celular));
    }
    
    @GetMapping("/direcciones/{id}/contactos")
    public ResponseEntity<ListaContactosDTO> getAllByDireccion(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoservice.findAllByDireccion(id));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody ContactoAddDTO contactodto) {
//        return ResponseEntity.ok(contactoservice.update(id, contactodto));
//    }
}

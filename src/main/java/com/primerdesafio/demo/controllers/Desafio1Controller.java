package com.primerdesafio.demo.controllers;

import com.primerdesafio.demo.model.entities.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Desafio1Controller {

    // consignas desafio
    @GetMapping("/hello")
    public String hello() {
        return "Hola! Mi nombre es Evelyn Arias y esta es mi primera API";
    }

    @PostMapping("/goodbye")
    public String goodbye() {
        return "Hasta luego!";
    }

    // extras
    @GetMapping("/hola1")
    public String hola1(@RequestParam String nombre) {
        return "Hola! Mi nombre es " + nombre;
    }

    @GetMapping("/hola2/{nombre}")
    public String hola2(@PathVariable String nombre) {
        return "Hola! Soy " + nombre;
    }

    @GetMapping("/hola3")
    public String hola3(@RequestParam(value = "nombre", defaultValue = "Mundo") String nombre) {
        return String.format("Hola %s", nombre);
    }

    @GetMapping("/hola4")
    public ResponseEntity<String> hola4() {
        return ResponseEntity.status(HttpStatus.OK).body("Hola desde el Response Entity");
    }

    @PostMapping("/persona")
    public String verPersona(@RequestBody Persona persona) {
        Persona p1 = new Persona(persona.getNombre(), persona.getApellido());
        return p1.toString();
    }

}

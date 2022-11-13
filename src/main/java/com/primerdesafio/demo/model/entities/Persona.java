package com.primerdesafio.demo.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Persona {

    private String nombre;
    private String apellido;

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
    
}

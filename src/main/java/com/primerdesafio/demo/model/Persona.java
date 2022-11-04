package com.primerdesafio.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Persona {

    String nombre;
    String apellido;

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}

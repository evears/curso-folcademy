package com.curso.demo.model.domain.contactoDTO;

import com.curso.demo.model.domain.direccionDTO.DireccionAddDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
public class ContactoAddDTO {
    private String nombre;
    private String celular;
    private DireccionAddDTO direccion;

}

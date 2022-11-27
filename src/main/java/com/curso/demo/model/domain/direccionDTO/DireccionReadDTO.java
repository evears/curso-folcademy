package com.curso.demo.model.domain.direccionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
public class DireccionReadDTO {
    private Integer id;
    private String calle;
    private String numero;
}

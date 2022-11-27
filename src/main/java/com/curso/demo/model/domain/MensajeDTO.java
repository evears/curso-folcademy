package com.curso.demo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
public class MensajeDTO {
    private Integer id;
    private String mensaje;
    
}

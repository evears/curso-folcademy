package com.primerdesafio.demo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
public class ContactoDTO {
    private Integer id;
    private String nombre;
    private Integer celular;

}

package com.curso.demo.exceptions.dtos;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ErrorMessageDTO {
    
    private Integer codigo;
    private String mensaje;
    private String path;
    
}

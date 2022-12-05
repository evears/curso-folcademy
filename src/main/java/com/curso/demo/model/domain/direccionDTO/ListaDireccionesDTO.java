package com.curso.demo.model.domain.direccionDTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
public class ListaDireccionesDTO {
    List<DireccionReadDTO> direcciones;
}

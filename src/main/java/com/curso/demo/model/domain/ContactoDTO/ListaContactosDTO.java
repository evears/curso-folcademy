package com.curso.demo.model.domain.contactoDTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
public class ListaContactosDTO {
    
    List<ContactoReadDTO> contactos;
    
}

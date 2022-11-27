package com.curso.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="direcciones")
@Data @NoArgsConstructor @AllArgsConstructor 
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED")
    private Integer id;
    
    @Column(name = "calle", columnDefinition = "VARCHAR(50)")
    private String calle;
    
    @Column(name = "numero", columnDefinition = "VARCHAR(8)")
    private String numero;
    
    
}

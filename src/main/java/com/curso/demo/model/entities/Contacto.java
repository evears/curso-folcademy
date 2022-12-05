package com.curso.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="contactos")
@Data @NoArgsConstructor @AllArgsConstructor 
public class Contacto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(50)")
    private String nombre;
    
    @Column(name = "celular", columnDefinition = "INT UNSIGNED")
    private String celular;
    
    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;
    
}

package com.curso.demo.model.repositories;

import com.curso.demo.model.entities.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
    public boolean existsByNombre(String nombre);
    public boolean existsByCelular(Integer celular);
    
}

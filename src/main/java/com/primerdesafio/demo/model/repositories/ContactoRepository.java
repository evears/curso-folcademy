package com.primerdesafio.demo.model.repositories;

import com.primerdesafio.demo.model.entities.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
    public boolean existsByNombre(String nombre);
    public boolean existsByCelular(Integer celular);
    
}

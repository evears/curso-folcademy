package com.curso.demo.model.repositories;

import com.curso.demo.model.entities.Contacto;
import com.curso.demo.model.entities.Direccion;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
    public boolean existsByNombre(String nombre);
    public boolean existsByCelular(String celular);
    Page<Contacto> findByNombreContainingAndCelularContaining(Pageable pageable, String nombre, String celular);
    List<Contacto> findAllByDireccion(Direccion direccion);
    
}

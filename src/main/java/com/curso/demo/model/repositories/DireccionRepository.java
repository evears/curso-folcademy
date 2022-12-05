package com.curso.demo.model.repositories;

import com.curso.demo.model.entities.Direccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer>  {
    public boolean existsByCalleAndNumero(String calle, String numero);
    
    public Direccion findByCalleAndNumero(String calle, String numero);

    Page<Direccion> findByCalleContainingAndNumeroContaining(Pageable pageable, String calle, String numero);
}

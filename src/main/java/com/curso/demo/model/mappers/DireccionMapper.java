package com.curso.demo.model.mappers;

import com.curso.demo.model.domain.direccionDTO.DireccionAddDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionReadDTO;
import com.curso.demo.model.entities.Direccion;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class DireccionMapper {

     public Direccion direccionReadDTOToDireccion(DireccionReadDTO direccionRead) {
        Direccion direccion = new Direccion();
        direccion.setId(direccionRead.getId());
        direccion.setCalle(direccionRead.getCalle());
        direccion.setNumero(direccionRead.getNumero());
        return direccion;
    }
         
    public Direccion direccionAddDTOToDireccion(DireccionAddDTO direccionAddDTO) {
        Direccion direccion = new Direccion();
        direccion.setCalle(direccionAddDTO.getCalle());
        direccion.setNumero(direccionAddDTO.getNumero());   
        return direccion;
    }

    public DireccionReadDTO direccionToDireccionReadDTO(Direccion direccion) {
        return Optional
                .ofNullable(direccion)
                .map(entity -> new DireccionReadDTO(
                entity.getId(),
                entity.getCalle(),
                entity.getNumero()))
                .orElse(new DireccionReadDTO());
    }

}

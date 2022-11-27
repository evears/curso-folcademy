package com.curso.demo.model.mappers;

import com.curso.demo.model.domain.contactoDTO.ContactoAddDTO;
import com.curso.demo.model.domain.MensajeDTO;
import com.curso.demo.model.domain.contactoDTO.ContactoReadDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionAddDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionReadDTO;
import com.curso.demo.model.entities.Contacto;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ContactoMapper {
    
    private final DireccionMapper direccionmapper;

    public ContactoMapper(DireccionMapper direccionmapper) {
        this.direccionmapper = direccionmapper;
    }

    // de ContactoADD a Contacto Entidad
    public Contacto contactoAddDTOToContacto(ContactoAddDTO contactoAddDTO, DireccionReadDTO direccionread) {
        Contacto contacto = new Contacto();
        contacto.setNombre(contactoAddDTO.getNombre());
        contacto.setCelular(contactoAddDTO.getCelular());
        contacto.setDireccion(direccionmapper.direccionReadDTOToDireccion(direccionread));
        return contacto;
    }

    // de ContactoRead a Contacto Entidad 
    public Contacto contactoReadDTOToContacto(ContactoReadDTO contactoReadDTO) {
        Contacto contacto = new Contacto();
        contacto.setId(contactoReadDTO.getId());
        contacto.setNombre(contactoReadDTO.getNombre());
        contacto.setCelular(contactoReadDTO.getCelular());
        contacto.setDireccion(direccionmapper.direccionReadDTOToDireccion(contactoReadDTO.getDireccion()));
        return contacto;
    }

    // de Contacto Entidad a ContactoRead
    public ContactoReadDTO contactoToContactoReadDTO(Contacto contacto) {
        return Optional
                .ofNullable(contacto)
                .map(entity -> new ContactoReadDTO(
                        entity.getId(), 
                        entity.getNombre(), 
                        entity.getCelular(),
                        direccionmapper.direccionToDireccionReadDTO(entity.getDireccion())))
                .orElse(new ContactoReadDTO());
    }

    // transforma a mensaje
    public MensajeDTO toMensaje(ContactoReadDTO contactoDTO) {
        return Optional
                .ofNullable(contactoDTO)
                .map(contacto -> new MensajeDTO(contacto.getId(), "Añadido exitosamente"))
                .orElse(new MensajeDTO());
    }

}

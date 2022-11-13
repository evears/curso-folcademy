package com.primerdesafio.demo.model.mappers;

import com.primerdesafio.demo.model.domain.ContactoDTO;
import com.primerdesafio.demo.model.entities.Contacto;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ContactoMapper {

    public Contacto contactoDTOToContactoEntity(ContactoDTO contactoDTO) {
        Contacto contacto = new Contacto();
        contacto.setId(contactoDTO.getId());
        contacto.setNombre(contactoDTO.getNombre());
        contacto.setCelular(contactoDTO.getCelular());
        return contacto;
    }

    public ContactoDTO contactoToContactoDTO(Contacto contacto) {
        return Optional
                .ofNullable(contacto)
                .map(entity -> new ContactoDTO(entity.getId(), entity.getNombre(), entity.getCelular()))
                .orElse(new ContactoDTO());
    }
}

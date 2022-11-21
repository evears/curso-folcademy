package com.primerdesafio.demo.services;

import com.primerdesafio.demo.exceptions.exceptionskinds.ContactoExistenteException;
import com.primerdesafio.demo.exceptions.exceptionskinds.ContactoInexistenteException;
import com.primerdesafio.demo.model.domain.ContactoDTO;
import com.primerdesafio.demo.model.domain.MensajeDTO;
import com.primerdesafio.demo.model.entities.Contacto;
import com.primerdesafio.demo.model.mappers.ContactoMapper;
import com.primerdesafio.demo.model.repositories.ContactoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ContactoService {

    private final ContactoRepository contactorepo;
    private final ContactoMapper contactomapper;

    public ContactoService(ContactoRepository contactorepo, ContactoMapper contactomapper) {
        this.contactorepo = contactorepo;
        this.contactomapper = contactomapper;
    }

    public List<ContactoDTO> findAll() {
        return contactorepo
                .findAll()
                .stream()
                .map(contacto -> contactomapper.contactoToContactoDTO(contacto))
                .collect(Collectors.toList());
    }

    public MensajeDTO add(ContactoDTO contactodto) {
        if (contactorepo.existsByNombre(contactodto.getNombre())) {
            throw new ContactoExistenteException("Ya existe un contacto con ese nombre.");
        } else if (contactorepo.existsByCelular(contactodto.getCelular())) {
            throw new ContactoExistenteException("Ya existe un contacto con ese celular.");
        } else {
            return Optional
                    .ofNullable(contactodto)
                    .map(dto -> contactomapper.contactoDTOToContactoEntity(dto))
                    .map(entity -> contactorepo.save(entity))
                    .map(entity -> contactomapper.contactoToContactoDTO(entity))
                    .map(entity -> contactomapper.toMensaje(entity))
                    .orElse(new MensajeDTO());
        }
    }

    public ContactoDTO findById(Integer id) {
        return contactorepo
                .findById(id)
                .map(contacto -> contactomapper.contactoToContactoDTO(contacto))
                .orElseThrow(() -> new ContactoInexistenteException("El contacto con el ID " + id + " no existe."));
    }

    public String delete(Integer id) {
        contactorepo.deleteById(id);
        return "Contacto eliminado.";
    }

    public String update(Integer id, ContactoDTO contactodto) {
        ContactoDTO dtoencontrado = contactorepo
                .findById(id)
                .map(contacto -> contactomapper.contactoToContactoDTO(contacto))
                .get();

        dtoencontrado.setCelular(contactodto.getCelular());
        dtoencontrado.setNombre(contactodto.getNombre());

        Contacto nuevo = contactomapper.contactoDTOToContactoEntity(dtoencontrado);
        Contacto guardado = contactorepo.save(nuevo);
        contactomapper.contactoToContactoDTO(guardado);

        return "Modificado exitosamente.";
    }

}

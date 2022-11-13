package com.primerdesafio.demo.services;

import com.primerdesafio.demo.model.domain.ContactoDTO;
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

    public ContactoDTO findOne(Integer id) {
        return contactorepo
                .findById(id)
                .map(contacto -> contactomapper.contactoToContactoDTO(contacto))
                .get();
    }

    public ContactoDTO add(ContactoDTO contactodto) {
        return Optional
                .ofNullable(contactodto)
                .map(dto -> contactomapper.contactoDTOToContactoEntity(dto))
                .map(entity -> contactorepo.save(entity))
                .map(entity -> contactomapper.contactoToContactoDTO(entity))
                .orElse(new ContactoDTO());
    }

    public void delete(Integer id) {
        contactorepo.deleteById(id);
    }

}

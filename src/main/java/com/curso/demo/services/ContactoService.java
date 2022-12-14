package com.curso.demo.services;

import com.curso.demo.exceptions.exceptionskinds.ContactoExistenteException;
import com.curso.demo.exceptions.exceptionskinds.ContactoInexistenteException;
import com.curso.demo.model.domain.contactoDTO.ContactoAddDTO;
import com.curso.demo.model.domain.MensajeDTO;
import com.curso.demo.model.domain.contactoDTO.ContactoReadDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionAddDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionReadDTO;
import com.curso.demo.model.entities.Contacto;
import com.curso.demo.model.entities.Direccion;
import com.curso.demo.model.mappers.ContactoMapper;
import com.curso.demo.model.mappers.DireccionMapper;
import com.curso.demo.model.repositories.ContactoRepository;
import com.curso.demo.model.repositories.DireccionRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ContactoService {

    private final ContactoRepository contactorepo;
    private final DireccionRepository direccionrepo;
    private final ContactoMapper contactomapper;
    private final DireccionMapper direccionmapper;

    public ContactoService(ContactoRepository contactorepo, DireccionRepository direccionrepo, ContactoMapper contactomapper, DireccionMapper direccionmapper) {
        this.contactorepo = contactorepo;
        this.direccionrepo = direccionrepo;
        this.contactomapper = contactomapper;
        this.direccionmapper = direccionmapper;
    }

    public List<ContactoReadDTO> findAll() {
        return contactorepo
                .findAll()
                .stream()
                .map(contacto -> contactomapper.contactoToContactoReadDTO(contacto))
                .collect(Collectors.toList());
    }

    public MensajeDTO add(ContactoAddDTO contactoadd) {
        DireccionAddDTO direccionadd = contactoadd.getDireccion();
        Direccion direccion = direccionmapper.direccionAddDTOToDireccion(direccionadd);
        direccionrepo.save(direccion);
        
        DireccionReadDTO direccionread = direccionmapper.direccionToDireccionReadDTO(direccion);

        if (contactorepo.existsByNombre(contactoadd.getNombre())) {
            throw new ContactoExistenteException("Ya existe un contacto con ese nombre.");
        } else if (contactorepo.existsByCelular(contactoadd.getCelular())) {
            throw new ContactoExistenteException("Ya existe un contacto con ese celular.");
        } else {
            return Optional
                    .ofNullable(contactoadd)
                    .map(contactoadd1 -> contactomapper.contactoAddDTOToContacto(contactoadd1, direccionread))
                    .map(contactoadd1 -> contactorepo.save(contactoadd1))
                    .map(contactoadd1 -> contactomapper.contactoToContactoReadDTO(contactoadd1))
                    .map(entity -> contactomapper.toMensaje(entity))
                    .orElse(new MensajeDTO());
        }
    }

    public ContactoReadDTO findById(Integer id) {
        return contactorepo
                .findById(id)
                .map(contacto -> contactomapper.contactoToContactoReadDTO(contacto))
                .orElseThrow(() -> new ContactoInexistenteException("El contacto con el ID " + id + " no existe."));
    }

    public String delete(Integer id) {
        contactorepo.deleteById(id);
        return "Contacto eliminado.";
    }

//    public String update(Integer id, ContactoAddDTO contactodto) {
//        ContactoReadDTO dtoencontrado = contactorepo
//                .findById(id)
//                .map(contacto -> contactomapper.contactoToContactoReadDTO(contacto))
//                .get();
//
//        dtoencontrado.setCelular(contactodto.getCelular());
//        dtoencontrado.setNombre(contactodto.getNombre());
//
//        Contacto nuevo = contactomapper.contactoReadDTOToContacto(dtoencontrado);
//        Contacto guardado = contactorepo.save(nuevo);
//        contactomapper.contactoToContactoReadDTO(guardado);
//
//        return "Modificado exitosamente.";
//    }
}

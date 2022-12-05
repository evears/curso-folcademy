package com.curso.demo.services;

import com.curso.demo.exceptions.exceptionskinds.ContactoExistenteException;
import com.curso.demo.exceptions.exceptionskinds.ContactoInexistenteException;
import com.curso.demo.model.domain.contactoDTO.ContactoAddDTO;
import com.curso.demo.model.domain.MensajeDTO;
import com.curso.demo.model.domain.contactoDTO.ContactoReadDTO;
import com.curso.demo.model.domain.contactoDTO.ListaContactosDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionAddDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionReadDTO;
import com.curso.demo.model.entities.Contacto;
import com.curso.demo.model.entities.Direccion;
import com.curso.demo.model.mappers.ContactoMapper;
import com.curso.demo.model.mappers.DireccionMapper;
import com.curso.demo.model.repositories.ContactoRepository;
import com.curso.demo.model.repositories.DireccionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactoService {

    private final ContactoRepository contactorepo;
    private final DireccionRepository direccionrepo;
    private final DireccionService direccionservice;
    private final ContactoMapper contactomapper;
    private final DireccionMapper direccionmapper;

    public ContactoService(ContactoRepository contactorepo, DireccionRepository direccionrepo, DireccionService direccionservice, ContactoMapper contactomapper, DireccionMapper direccionmapper) {
        this.contactorepo = contactorepo;
        this.direccionrepo = direccionrepo;
        this.direccionservice = direccionservice;
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
        
        if (contactorepo.existsByNombre(contactoadd.getNombre())) {
            throw new ContactoExistenteException("Ya existe un contacto con ese nombre.");
        } else if (contactorepo.existsByCelular(contactoadd.getCelular())) {
            throw new ContactoExistenteException("Ya existe un contacto con ese celular.");
        } else if (direccionrepo.existsByCalleAndNumero(direccion.getCalle(), direccion.getNumero())) {
            DireccionReadDTO direccionencontrada = 
                    direccionmapper.direccionToDireccionReadDTO(
                            direccionrepo.findByCalleAndNumero(direccion.getCalle(), direccion.getNumero()));

            return Optional
                    .ofNullable(contactoadd)
                    .map(contactoadd1 -> contactomapper.contactoAddDTOToContacto(contactoadd1, direccionencontrada))
                    .map(contactoadd1 -> contactorepo.save(contactoadd1))
                    .map(contactoadd1 -> contactomapper.contactoToContactoReadDTO(contactoadd1))
                    .map(entity -> contactomapper.toMensaje(entity, "Añadido exitosamente."))
                    .orElse(new MensajeDTO());
        } else {
            direccionrepo.save(direccion);
            DireccionReadDTO direccionread = direccionmapper.direccionToDireccionReadDTO(direccion);
            
            return Optional
                    .ofNullable(contactoadd)
                    .map(contactoadd1 -> contactomapper.contactoAddDTOToContacto(contactoadd1, direccionread))
                    .map(contactoadd1 -> contactorepo.save(contactoadd1))
                    .map(contactoadd1 -> contactomapper.contactoToContactoReadDTO(contactoadd1))
                    .map(entity -> contactomapper.toMensaje(entity, "Añadido exitosamente."))
                    .orElse(new MensajeDTO());
        }
    }

    public ContactoReadDTO findById(Integer id) {
        return contactorepo
                .findById(id)
                .map(contacto -> contactomapper.contactoToContactoReadDTO(contacto))
                .orElseThrow(() -> new ContactoInexistenteException("El contacto con el ID " + id + " no existe."));
    }

    public MensajeDTO delete(Integer id) {
        contactorepo.deleteById(id);
        return new MensajeDTO(id, "Eliminado correctamente");
    }

    public Page<ContactoReadDTO> findAllWithPagitation(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return contactorepo.findAll(pageable)
                .map(contacto -> contactomapper.contactoToContactoReadDTO(contacto));

    }

    public ListaContactosDTO findAllWithFilters(Integer page, Integer size, String nombre, String celular) {
        Pageable pageable = PageRequest.of(page, size);
        return new ListaContactosDTO(contactorepo
                .findByNombreContainingAndCelularContaining(pageable, nombre, celular)
                .stream()
                .map(contacto -> contactomapper.contactoToContactoReadDTO(contacto))
                .collect(Collectors.toList()));
    }
    
    
    public ListaContactosDTO findAllByDireccion(Integer id) {
        DireccionReadDTO direccionread = direccionservice.findById(id);
        Direccion direccion = direccionmapper.direccionReadDTOToDireccion(direccionread);
        
        return new ListaContactosDTO (contactorepo.findAllByDireccion(direccion)
                .stream()
                .map(contacto -> contactomapper.contactoToContactoReadDTO(contacto))
                .collect(Collectors.toList()));
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

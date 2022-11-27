package com.curso.demo.services;

import com.curso.demo.model.domain.contactoDTO.ContactoAddDTO;
import com.curso.demo.model.domain.contactoDTO.ContactoReadDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionAddDTO;
import com.curso.demo.model.domain.direccionDTO.DireccionReadDTO;
import com.curso.demo.model.mappers.DireccionMapper;
import com.curso.demo.model.repositories.DireccionRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {
    
    private final DireccionRepository direccionrepo;
    private final DireccionMapper direccionmapper;

    public DireccionService(DireccionRepository direccionrepo, DireccionMapper direccionmapper) {
        this.direccionrepo = direccionrepo;
        this.direccionmapper = direccionmapper;;
    }

//   public DireccionReadDTO add(ContactoAddDTO contactoAddDTO){
//        return Optional
//                    .ofNullable(contactoAddDTO)
//                    .map(dto -> direccionmapper.direccionAddDTOToDireccion(dto))
//                    .map(entity -> direccionrepo.save(entity))
//                    .map(entity -> direccionmapper.direccionToDireccionReadDTO(entity))
//                    .orElse(new DireccionReadDTO());
//    }
   
    public List<DireccionReadDTO> findAll(){
        return direccionrepo
                .findAll()
                .stream()
                .map(direccion -> {
                    System.out.println();
                    return direccionmapper.direccionToDireccionReadDTO(direccion);
                })
                .collect(Collectors.toList());
    }
    
    public DireccionReadDTO findById(Integer id) {
        return direccionrepo
                .findById(id)
                .map(direccion -> direccionmapper.direccionToDireccionReadDTO(direccion))
                .get();
    }
    

}

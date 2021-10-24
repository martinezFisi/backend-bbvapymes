package com.bbva.pymesbbva.mapper;

import com.bbva.pymesbbva.dto.RepresentanteLegalDTO;
import com.bbva.pymesbbva.entity.RepresentanteLegalEntity;
import org.springframework.stereotype.Component;

@Component
public class RepresentanteLegalMapper {

    public RepresentanteLegalDTO entityToDTO(RepresentanteLegalEntity representanteLegalEntity){
        return RepresentanteLegalDTO.builder()
                .dni(representanteLegalEntity.getDni())
                .nombres(representanteLegalEntity.getNombres())
                .apellidos(representanteLegalEntity.getApellidos())
                .estadoCivil(representanteLegalEntity.getEstadoCivil())
                .edad(representanteLegalEntity.getEdad())
                .sexo(representanteLegalEntity.getSexo())
                .direccion(representanteLegalEntity.getDireccion())
                .tipo(representanteLegalEntity.getTipo())
                .build();
    }

}

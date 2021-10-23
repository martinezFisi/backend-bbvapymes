package com.bbva.pymesbbva.mapper;

import com.bbva.pymesbbva.dto.ClienteEmpresaDTO;
import com.bbva.pymesbbva.entity.ClienteEmpresaEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteEmpresaMapper {

    public ClienteEmpresaDTO entityToDTO(ClienteEmpresaEntity clienteEmpresaEntity){
        return ClienteEmpresaDTO.builder()
                .ruc(clienteEmpresaEntity.getRuc())
                .razonSocial(clienteEmpresaEntity.getRazonSocial())
                .rubro(clienteEmpresaEntity.getRubro())
                .fechaInicioActividad(clienteEmpresaEntity.getFechaInicioActividad())
                .estado(clienteEmpresaEntity.getEstado())
                .nivelClasificacion(clienteEmpresaEntity.getNivelCalificacion())
                .direccionFiscal(clienteEmpresaEntity.getDireccionFiscal())
                .gerenteGeneral(clienteEmpresaEntity.getGerenteGeneral())
                .gerenteAdministrativo(clienteEmpresaEntity.getGerenteAdministrativo())
                .build();
    }

}

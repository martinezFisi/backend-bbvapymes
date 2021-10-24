package com.bbva.pymesbbva.mapper;

import com.bbva.pymesbbva.dto.SolicitudDTO;
import com.bbva.pymesbbva.entity.SolicitudEntity;
import org.springframework.stereotype.Component;

@Component
public class SolicitudMapper {

    public SolicitudEntity dtoToEntity(SolicitudDTO solicitudDTO) {
        return SolicitudEntity.builder()
                              .rucDni(solicitudDTO.getRuc().concat("-").concat(solicitudDTO.getDni()))
                              .clienteEmpresaRazonSocial(solicitudDTO.getClienteEmpresaRazonSocial())
                              .clienteEmpresaRubro(solicitudDTO.getClienteEmpresaRubro())
                              .clienteEmpresaFechaInicioActividades(solicitudDTO.getClienteEmpresaFechaInicioActividades())
                              .clienteEmpresaEstado(solicitudDTO.getClienteEmpresaEstado())
                              .clienteEmpresaDireccionFiscal(solicitudDTO.getClienteEmpresaDireccionFiscal())
                              .clienteEmpresaNivelCalificacion(solicitudDTO.getClienteEmpresaNivelCalificacion())
                              .clienteEmpresaGerenteGeneral(solicitudDTO.getClienteEmpresaGerenteGeneral())
                              .clienteEmpresaGerenteAdministrativo(solicitudDTO.getClienteEmpresaGerenteAdministrativo())
                              .representanteLegalNombres(solicitudDTO.getRepresentanteLegalNombres())
                              .representanteLegalApellidos(solicitudDTO.getRepresentanteLegalApellidos())
                              .representanteLegalEstadoCivil(solicitudDTO.getRepresentanteLegalEstadoCivil())
                              .representanteLegalEdad(solicitudDTO.getRepresentanteLegalEdad())
                              .representanteLegalSexo(solicitudDTO.getRepresentanteLegalSexo())
                              .representanteLegalTipo(solicitudDTO.getRepresentanteLegalTipo())
                              .cartaPoderPoderdante(solicitudDTO.getCartaPoderPoderdante())
                              .cartaPoderEstado(solicitudDTO.getCartaPoderEstado())
                              .tipoServicio(solicitudDTO.getTipoServicio())
                              .producto(solicitudDTO.getProducto())
                              .build();
    }

    public SolicitudDTO entityToDTO(SolicitudEntity solicitudEntity) {
        return SolicitudDTO.builder()
                           .ruc(solicitudEntity.getRucDni().split("-")[0])
                           .dni(solicitudEntity.getRucDni().split("-")[1])
                           .clienteEmpresaRazonSocial(solicitudEntity.getClienteEmpresaRazonSocial())
                           .clienteEmpresaRubro(solicitudEntity.getClienteEmpresaRubro())
                           .clienteEmpresaFechaInicioActividades(solicitudEntity.getClienteEmpresaFechaInicioActividades())
                           .clienteEmpresaEstado(solicitudEntity.getClienteEmpresaEstado())
                           .clienteEmpresaDireccionFiscal(solicitudEntity.getClienteEmpresaDireccionFiscal())
                           .clienteEmpresaNivelCalificacion(solicitudEntity.getClienteEmpresaNivelCalificacion())
                           .clienteEmpresaGerenteGeneral(solicitudEntity.getClienteEmpresaGerenteGeneral())
                           .clienteEmpresaGerenteAdministrativo(solicitudEntity.getClienteEmpresaGerenteAdministrativo())
                           .representanteLegalNombres(solicitudEntity.getRepresentanteLegalNombres())
                           .representanteLegalApellidos(solicitudEntity.getRepresentanteLegalApellidos())
                           .representanteLegalEstadoCivil(solicitudEntity.getRepresentanteLegalEstadoCivil())
                           .representanteLegalEdad(solicitudEntity.getRepresentanteLegalEdad())
                           .representanteLegalSexo(solicitudEntity.getRepresentanteLegalSexo())
                           .representanteLegalTipo(solicitudEntity.getRepresentanteLegalTipo())
                           .cartaPoderPoderdante(solicitudEntity.getCartaPoderPoderdante())
                           .cartaPoderEstado(solicitudEntity.getCartaPoderEstado())
                           .tipoServicio(solicitudEntity.getTipoServicio())
                           .producto(solicitudEntity.getProducto())
                           .build();
    }

}

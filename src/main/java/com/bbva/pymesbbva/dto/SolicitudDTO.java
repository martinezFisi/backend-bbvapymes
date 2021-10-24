package com.bbva.pymesbbva.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SolicitudDTO {

    private String ruc;
    private String dni;
    private String clienteEmpresaRazonSocial;
    private String clienteEmpresaRubro;
    private String clienteEmpresaFechaInicioActividades;
    private String clienteEmpresaEstado;
    private String clienteEmpresaDireccionFiscal;
    private String clienteEmpresaNivelCalificacion;
    private String clienteEmpresaGerenteGeneral;
    private String clienteEmpresaGerenteAdministrativo;
    private String representanteLegalNombres;
    private String representanteLegalApellidos;
    private String representanteLegalEstadoCivil;
    private Integer representanteLegalEdad;
    private String representanteLegalSexo;
    private String representanteLegalTipo;
    private String cartaPoderPoderdante;
    private String cartaPoderEstado;
    private String tipoServicio;
    private String producto;

}

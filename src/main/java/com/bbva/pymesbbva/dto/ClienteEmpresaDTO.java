package com.bbva.pymesbbva.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClienteEmpresaDTO {

    private String ruc;
    private String razonSocial;
    private String rubro;
    private String fechaInicioActividad;
    private String estado;
    private String nivelClasificacion;
    private String direccionFiscal;
    private String gerenteGeneral;
    private String gerenteAdministrativo;

}

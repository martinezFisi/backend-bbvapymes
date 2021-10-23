package com.bbva.pymesbbva.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RepresentanteLegalDTO {

    private String dni;
    private String nombres;
    private String apellidos;
    private String estadoCivil;
    private Integer edad;
    private String sexo;
    private String direccion;

}

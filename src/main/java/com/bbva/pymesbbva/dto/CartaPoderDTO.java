package com.bbva.pymesbbva.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartaPoderDTO {

    private String numeroPartida;
    private String empresa;
    private String poderdante;
    private String rolPoderdante;
    private String tipo;
    private String estado;
    private String apoderado;

}

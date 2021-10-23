package com.bbva.pymesbbva.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CARTA_PODER")
@Table(name = "CARTA_PODER", schema = "pymesbbva")
public class CartaPoderEntity {

    @Id
    @Column(name = "NUMERO_PARTIDA")
    private String numeroPartida;
    @Column(name = "EMPRESA")
    private String empresa;
    @Column(name = "PODERDANTE")
    private String poderdante;
    @Column(name = "ROL_PODERDANTE")
    private String rolPoderdante;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "APODERADO")
    private String apoderado;

}

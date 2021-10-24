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
@Entity(name = "REPRESENTANTE_LEGAL")
@Table(name = "REPRESENTANTE_LEGAL", schema = "pymesbbva")
public class RepresentanteLegalEntity {

    @Id
    @Column(name = "DNI")
    private String dni;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    @Column(name = "EDAD")
    private Integer edad;
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TIPO")
    private String tipo;

}

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
@Entity(name = "CLIENTE_EMPRESA")
@Table(name = "CLIENTE_EMPRESA", schema = "pymesbbva")
public class ClienteEmpresaEntity {

    @Id
    @Column(name = "RUC")
    private String ruc;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "RUBRO")
    private String rubro;
    @Column(name = "FECHA_INICIO_ACTIVIDAD")
    private String fechaInicioActividad;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "NIVEL_CALIFICACION")
    private String nivelCalificacion;
    @Column(name = "DIRECCION_FISCAL")
    private String direccionFiscal;
    @Column(name = "GERENTE_GENERAL")
    private String gerenteGeneral;
    @Column(name = "GERENTE_ADMINISTRATIVO")
    private String gerenteAdministrativo;

}

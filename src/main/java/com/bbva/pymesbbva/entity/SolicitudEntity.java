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
@Entity(name = "SOLICITUD")
@Table(name = "SOLICITUD", schema = "pymesbbva")
public class SolicitudEntity {

    @Id
    @Column(name = "CLIENTE_EMPRESA_RUC_REPRESENTANTE_LEGAL_DNI")
    private String rucDni;
    @Column(name = "CLIENTE_EMPRESA_RAZON_SOCIAL")
    private String clienteEmpresaRazonSocial;
    @Column(name = "CLIENTE_EMPRESA_RUBRO")
    private String clienteEmpresaRubro;
    @Column(name = "CLIENTE_EMPRESA_FECHA_INICIO_ACTIVIDADES")
    private String clienteEmpresaFechaInicioActividades;
    @Column(name = "CLIENTE_EMPRESA_ESTADO")
    private String clienteEmpresaEstado;
    @Column(name = "CLIENTE_EMPRESA_DIRECCION_FISCAL")
    private String clienteEmpresaDireccionFiscal;
    @Column(name = "CLIENTE_EMPRESA_NIVEL_CALIFICACION")
    private String clienteEmpresaNivelCalificacion;
    @Column(name = "CLIENTE_EMPRESA_GERENTE_GENERAL")
    private String clienteEmpresaGerenteGeneral;
    @Column(name = "CLIENTE_EMPRESA_GERENTE_ADMINISTRATIVO")
    private String clienteEmpresaGerenteAdministrativo;
    @Column(name = "REPRESENTANTE_LEGAL_NOMBRES")
    private String representanteLegalNombres;
    @Column(name = "REPRESENTANTE_LEGAL_APELLIDOS")
    private String representanteLegalApellidos;
    @Column(name = "REPRESENTANTE_LEGAL_ESTADO_CIVIL")
    private String representanteLegalEstadoCivil;
    @Column(name = "REPRESENTANTE_LEGAL_EDAD")
    private Integer representanteLegalEdad;
    @Column(name = "REPRESENTANTE_LEGAL_SEXO")
    private String representanteLegalSexo;
    @Column(name = "REPRESENTANTE_LEGAL_TIPO")
    private String representanteLegalTipo;
    @Column(name = "CARTA_PODER_PODERDANTE")
    private String cartaPoderPoderdante;
    @Column(name = "CARTA_PODER_ESTADO")
    private String cartaPoderEstado;
    @Column(name = "TIPO_SERVICIO")
    private String tipoServicio;
    @Column(name = "PRODUCTO")
    private String producto;

}

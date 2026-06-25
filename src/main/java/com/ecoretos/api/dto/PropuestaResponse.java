package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PropuestaResponse {

    private Integer idPropuesta;
    private Integer idUsuario;
    private String nombreUsuario;
    private String titulo;
    private String descripcion;
    private String categoria;
    private String estado;
    private LocalDateTime fechaPropuesta;
    private String observacionAdmin;
}

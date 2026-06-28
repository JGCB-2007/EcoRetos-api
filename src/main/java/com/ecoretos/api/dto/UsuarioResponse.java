package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioResponse {

    private Integer idUsuario;
    private String cif;
    private String nombreCompleto;
    private String correoInstitucional;
    private String rol;
    private Integer puntosTotales;
}
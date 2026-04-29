package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private Integer idUsuario;
    private String cif;
    private String nombreCompleto;
    private String rol;
    private String mensaje;
}
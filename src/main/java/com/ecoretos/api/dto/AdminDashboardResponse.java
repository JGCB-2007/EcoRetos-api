package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminDashboardResponse {

    private Long retosActivos;
    private Long evidenciasPendientes;
    private Long insigniasActivas;
    private Long usuariosRegistrados;
    private Long propuestasPendientes;
}
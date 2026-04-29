package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccionEvidenciaResponse {

    private Integer idEvidencia;
    private String estado;
    private String mensaje;
}
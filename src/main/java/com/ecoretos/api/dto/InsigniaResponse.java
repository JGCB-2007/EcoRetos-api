package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InsigniaResponse {
    private Integer idInsignia;
    private String nombre;
    private String descripcion;
    private Integer puntosMinimos;
    private String iconoUrl;
    private Boolean activa;
}
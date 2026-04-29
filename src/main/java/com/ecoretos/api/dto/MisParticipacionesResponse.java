package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MisParticipacionesResponse {

    private Integer idParticipacion;
    private Integer idReto;
    private String tituloReto;
    private String descripcionReto;
    private Integer puntos;
    private String estado;
    private LocalDateTime fechaAceptacion;
}
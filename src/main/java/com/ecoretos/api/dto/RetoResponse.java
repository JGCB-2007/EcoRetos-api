package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RetoResponse {
    private Integer idReto;
    private String titulo;
    private String descripcion;
    private Integer puntos;
    private String dificultad;
    private String tipoValidacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer duracionHoras;
}
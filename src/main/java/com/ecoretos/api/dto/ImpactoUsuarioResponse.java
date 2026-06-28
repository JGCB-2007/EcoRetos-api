package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImpactoUsuarioResponse {

    private Integer puntosTotales;
    private Long retosCompletados;
    private Long retosCompletadosSemana;
    private Integer rachaDias;
    private Integer posicionRanking;
}
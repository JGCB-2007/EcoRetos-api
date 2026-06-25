package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankingResponse {
    private Integer idUsuario;
    private String nombreCompleto;
    private String cif;
    private Integer puntosTotales;
}
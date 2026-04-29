package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class EvidenciaResponse {
    private Integer idEvidencia;
    private Integer idParticipacion;
    private String urlImagen;
    private String estadoValidacion;
    private String resultadoIA;
    private BigDecimal confianzaIA;
    private String etiquetasDetectadas;
    private LocalDateTime fechaSubida;
    private String mensaje;
}
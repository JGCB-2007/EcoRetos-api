package com.ecoretos.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EvidenciaRequest {

    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String urlImagen;

    private String resultadoIA;
    private BigDecimal confianzaIA;
    private String etiquetasDetectadas;
}
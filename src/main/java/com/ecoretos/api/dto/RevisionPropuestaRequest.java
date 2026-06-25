package com.ecoretos.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevisionPropuestaRequest {

    @NotBlank(message = "La observación del administrador es obligatoria")
    private String observacionAdmin;

    // Solo se usa cuando la propuesta es aprobada
    private Integer puntos;

    private String dificultad;

    private String tipoValidacion;

    private Integer duracionHoras;

    private Integer idAdministrador;
}
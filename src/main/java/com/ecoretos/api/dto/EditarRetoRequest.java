package com.ecoretos.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarRetoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotNull
    @Min(1)
    private Integer puntos;

    @NotBlank
    private String dificultad;

    @NotBlank
    private String tipoValidacion;

    @NotNull
    @Min(1)
    private Integer duracionHoras;
}
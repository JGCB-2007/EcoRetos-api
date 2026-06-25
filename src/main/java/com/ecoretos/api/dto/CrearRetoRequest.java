package com.ecoretos.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearRetoRequest {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "Los puntos son obligatorios")
    @Min(value = 1, message = "Los puntos deben ser mayores que 0")
    private Integer puntos;

    @NotBlank(message = "La dificultad es obligatoria")
    private String dificultad;

    @NotBlank(message = "El tipo de validación es obligatorio")
    private String tipoValidacion;

    @NotNull(message = "El id del administrador es obligatorio")
    private Integer creadoPor;

    private Integer duracionHoras = 24;
}
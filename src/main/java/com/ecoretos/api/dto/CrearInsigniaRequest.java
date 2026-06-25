package com.ecoretos.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearInsigniaRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "Los puntos mínimos son obligatorios")
    @Min(value = 1, message = "Los puntos mínimos deben ser mayores que 0")
    private Integer puntosMinimos;

    private String iconoUrl;
}
package com.ecoretos.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearPropuestaRequest {

    @NotNull
    private Integer idUsuario;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotBlank
    private String categoria;
}
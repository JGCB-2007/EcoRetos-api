package com.ecoretos.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AceptarRetoRequest {

    @NotNull(message = "El id del usuario es obligatorio")
    private Integer idUsuario;
}
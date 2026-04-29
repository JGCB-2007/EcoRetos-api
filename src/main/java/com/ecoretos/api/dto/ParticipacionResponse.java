package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ParticipacionResponse {
    private Integer idParticipacion;
    private Integer idUsuario;
    private Integer idReto;
    private String tituloReto;
    private String estado;
    private LocalDateTime fechaAceptacion;
    private String mensaje;
}
package com.ecoretos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class EvidenciaAdminResponse {

    private Integer idEvidencia;
    private Integer idParticipacion;
    private String nombreUsuario;
    private String tituloReto;
    private String urlImagen;
    private String estadoValidacion;
    private LocalDateTime fechaSubida;
}
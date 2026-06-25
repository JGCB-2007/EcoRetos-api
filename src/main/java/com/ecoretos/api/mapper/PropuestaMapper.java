package com.ecoretos.api.mapper;

import com.ecoretos.api.dto.PropuestaResponse;
import com.ecoretos.api.entity.RetoPropuesto;

public class PropuestaMapper {

    public static PropuestaResponse toResponse(RetoPropuesto propuesta) {
        return new PropuestaResponse(
                propuesta.getIdPropuesta(),
                propuesta.getUsuario().getIdUsuario(),
                propuesta.getUsuario().getNombreCompleto(),
                propuesta.getTitulo(),
                propuesta.getDescripcion(),
                propuesta.getCategoria(),
                propuesta.getEstado(),
                propuesta.getFechaPropuesta(),
                propuesta.getObservacionAdmin()
        );
    }
}
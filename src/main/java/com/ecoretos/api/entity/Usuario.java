package com.ecoretos.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Integer idUsuario;

    @Column(name = "CIF", nullable = false, unique = true, length = 20)
    private String cif;

    @Column(name = "NombreCompleto", nullable = false, length = 150)
    private String nombreCompleto;

    @Column(name = "CorreoInstitucional", length = 150)
    private String correoInstitucional;

    @Column(name = "ContrasenaHash", nullable = false, length = 255)
    private String contrasenaHash;

    @Column(name = "Rol", nullable = false, length = 20)
    private String rol;

    @Column(name = "Activo", nullable = false)
    private Boolean activo;

    @Column(name = "PuntosTotales", nullable = false)
    private Integer puntosTotales;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;
}
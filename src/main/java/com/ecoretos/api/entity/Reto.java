package com.ecoretos.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Retos")
@Getter
@Setter
@NoArgsConstructor
public class Reto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdReto")
    private Integer idReto;

    @Column(name = "Titulo", nullable = false, length = 120)
    private String titulo;

    @Column(name = "Descripcion", nullable = false, length = 300)
    private String descripcion;

    @Column(name = "Puntos", nullable = false)
    private Integer puntos;

    @Column(name = "Dificultad", nullable = false, length = 20)
    private String dificultad;

    @Column(name = "TipoValidacion", nullable = false, length = 20)
    private String tipoValidacion;

    @Column(name = "Activo", nullable = false)
    private Boolean activo;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "FechaInicio")
    private LocalDateTime fechaInicio;

    @Column(name = "FechaFin")
    private LocalDateTime fechaFin;

    @Column(name = "DuracionHoras")
    private Integer duracionHoras;

    @ManyToOne
    @JoinColumn(name = "CreadoPor", nullable = false)
    private Usuario creadoPor;
}

package com.ecoretos.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Participaciones")
@Getter
@Setter
@NoArgsConstructor
public class Participacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdParticipacion")
    private Integer idParticipacion;

    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "IdReto", nullable = false)
    private Reto reto;

    @Column(name = "FechaAceptacion", nullable = false)
    private LocalDateTime fechaAceptacion;

    @Column(name = "Estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "FechaResolucion")
    private LocalDateTime fechaResolucion;

    @Column(name = "ObservacionAdmin", length = 300)
    private String observacionAdmin;
}
package com.ecoretos.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "RetosPropuestos")
@Getter
@Setter
@NoArgsConstructor
public class RetoPropuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPropuesta")
    private Integer idPropuesta;

    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "Titulo", nullable = false, length = 120)
    private String titulo;

    @Column(name = "Descripcion", nullable = false, length = 300)
    private String descripcion;

    @Column(name = "Categoria", nullable = false, length = 80)
    private String categoria;

    @Column(name = "Estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "FechaPropuesta", nullable = false)
    private LocalDateTime fechaPropuesta;

    @Column(name = "ObservacionAdmin", length = 300)
    private String observacionAdmin;
}
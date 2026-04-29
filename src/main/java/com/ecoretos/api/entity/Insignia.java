package com.ecoretos.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Insignias")
@Getter
@Setter
@NoArgsConstructor
public class Insignia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdInsignia")
    private Integer idInsignia;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Descripcion", nullable = false, length = 250)
    private String descripcion;

    @Column(name = "PuntosMinimos", nullable = false)
    private Integer puntosMinimos;

    @Column(name = "IconoUrl", length = 500)
    private String iconoUrl;

    @Column(name = "Activa", nullable = false)
    private Boolean activa;
}
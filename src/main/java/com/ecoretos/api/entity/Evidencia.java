package com.ecoretos.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Evidencias")
@Getter
@Setter
@NoArgsConstructor
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEvidencia")
    private Integer idEvidencia;

    @ManyToOne
    @JoinColumn(name = "IdParticipacion", nullable = false)
    private Participacion participacion;

    @Column(name = "UrlImagen", nullable = false, length = 500)
    private String urlImagen;

    @Column(name = "FechaSubida", nullable = false)
    private LocalDateTime fechaSubida;

    @Column(name = "EstadoValidacion", nullable = false, length = 20)
    private String estadoValidacion;

    @Column(name = "ResultadoIA", length = 50)
    private String resultadoIA;

    @Column(name = "ConfianzaIA")
    private BigDecimal confianzaIA;

    @Column(name = "EtiquetasDetectadas", length = 300)
    private String etiquetasDetectadas;

    @Column(name = "ObservacionAdmin", length = 300)
    private String observacionAdmin;
}

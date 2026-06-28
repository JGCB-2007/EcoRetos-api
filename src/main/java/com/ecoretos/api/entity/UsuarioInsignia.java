package com.ecoretos.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
@Table(name = "UsuarioInsignias")
@Getter
@Setter
@NoArgsConstructor
public class UsuarioInsignia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuarioInsignia")
    private Integer idUsuarioInsignia;

    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "IdInsignia", nullable = false)
    private Insignia insignia;

    @Column(name = "FechaAsignacion", nullable = false)
    private LocalDateTime fechaAsignacion;

    @PrePersist
    public void prePersist() {
        if (fechaAsignacion == null) {
            fechaAsignacion = java.time.LocalDateTime.now();
        }
    }
}

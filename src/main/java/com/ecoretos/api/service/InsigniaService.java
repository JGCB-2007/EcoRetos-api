package com.ecoretos.api.service;

import com.ecoretos.api.entity.Insignia;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.entity.UsuarioInsignia;
import com.ecoretos.api.repository.InsigniaRepository;
import com.ecoretos.api.repository.UsuarioInsigniaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InsigniaService {

    private final InsigniaRepository insigniaRepository;
    private final UsuarioInsigniaRepository usuarioInsigniaRepository;

    public InsigniaService(
            InsigniaRepository insigniaRepository,
            UsuarioInsigniaRepository usuarioInsigniaRepository
    ) {
        this.insigniaRepository = insigniaRepository;
        this.usuarioInsigniaRepository = usuarioInsigniaRepository;
    }

    public void asignarInsigniasSiCorresponde(Usuario usuario) {
        List<Insignia> insigniasDisponibles =
                insigniaRepository.buscarInsigniasDisponibles(usuario.getPuntosTotales());

        for (Insignia insignia : insigniasDisponibles) {
            boolean yaLaTiene = usuarioInsigniaRepository
                    .existsByUsuario_IdUsuarioAndInsignia_IdInsignia(
                            usuario.getIdUsuario(),
                            insignia.getIdInsignia()
                    );

            if (!yaLaTiene) {
                UsuarioInsignia usuarioInsignia = new UsuarioInsignia();
                usuarioInsignia.setUsuario(usuario);
                usuarioInsignia.setInsignia(insignia);
                usuarioInsignia.setFechaAsignacion(LocalDateTime.now());

                usuarioInsigniaRepository.save(usuarioInsignia);
            }
        }
    }
}
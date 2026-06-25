package com.ecoretos.api.service;

import com.ecoretos.api.dto.CrearInsigniaRequest;
import com.ecoretos.api.dto.InsigniaResponse;
import com.ecoretos.api.entity.Insignia;
import com.ecoretos.api.repository.InsigniaRepository;
import org.springframework.stereotype.Service;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.entity.UsuarioInsignia;
import com.ecoretos.api.repository.UsuarioInsigniaRepository;

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
    public List<InsigniaResponse> listarInsigniasPorUsuario(Integer idUsuario) {
        return usuarioInsigniaRepository.findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(usuarioInsignia -> convertirAResponse(usuarioInsignia.getInsignia()))
                .toList();
    }
    public InsigniaResponse crearInsignia(CrearInsigniaRequest request) {
        Insignia insignia = new Insignia();
        insignia.setNombre(request.getNombre());
        insignia.setDescripcion(request.getDescripcion());
        insignia.setPuntosMinimos(request.getPuntosMinimos());
        insignia.setIconoUrl(request.getIconoUrl());
        insignia.setActiva(true);

        Insignia guardada = insigniaRepository.save(insignia);

        return convertirAResponse(guardada);
    }

    public List<InsigniaResponse> listarInsigniasActivas() {
        return insigniaRepository.findByActivaTrueOrderByPuntosMinimosAsc()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private InsigniaResponse convertirAResponse(Insignia insignia) {
        return new InsigniaResponse(
                insignia.getIdInsignia(),
                insignia.getNombre(),
                insignia.getDescripcion(),
                insignia.getPuntosMinimos(),
                insignia.getIconoUrl(),
                insignia.getActiva()
        );

    }
    public void asignarInsigniasSiCorresponde(Usuario usuario) {
        List<Insignia> insignias = insigniaRepository.findByActivaTrueOrderByPuntosMinimosAsc();

        for (Insignia insignia : insignias) {
            boolean yaTiene = usuarioInsigniaRepository
                    .existsByUsuario_IdUsuarioAndInsignia_IdInsignia(
                            usuario.getIdUsuario(),
                            insignia.getIdInsignia()
                    );

            if (!yaTiene && usuario.getPuntosTotales() >= insignia.getPuntosMinimos()) {
                UsuarioInsignia usuarioInsignia = new UsuarioInsignia();
                usuarioInsignia.setUsuario(usuario);
                usuarioInsignia.setInsignia(insignia);

                usuarioInsigniaRepository.save(usuarioInsignia);
            }
        }
    }
}
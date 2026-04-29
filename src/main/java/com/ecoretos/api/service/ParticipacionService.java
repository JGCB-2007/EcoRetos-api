package com.ecoretos.api.service;

import com.ecoretos.api.dto.MisParticipacionesResponse;
import com.ecoretos.api.entity.Participacion;
import com.ecoretos.api.repository.ParticipacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipacionService {

    private final ParticipacionRepository participacionRepository;

    public ParticipacionService(ParticipacionRepository participacionRepository) {
        this.participacionRepository = participacionRepository;
    }

    public List<MisParticipacionesResponse> listarPorUsuario(Integer idUsuario) {
        List<Participacion> participaciones =
                participacionRepository.findByUsuario_IdUsuario(idUsuario);

        return participaciones.stream()
                .map(p -> new MisParticipacionesResponse(
                        p.getIdParticipacion(),
                        p.getReto().getIdReto(),
                        p.getReto().getTitulo(),
                        p.getReto().getDescripcion(),
                        p.getReto().getPuntos(),
                        p.getEstado(),
                        p.getFechaAceptacion()
                ))
                .toList();
    }
}
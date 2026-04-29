package com.ecoretos.api.service;

import com.ecoretos.api.dto.RetoResponse;
import com.ecoretos.api.entity.Reto;
import com.ecoretos.api.repository.ParticipacionRepository;
import com.ecoretos.api.repository.RetoRepository;
import com.ecoretos.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import com.ecoretos.api.dto.ParticipacionResponse;
import com.ecoretos.api.entity.Participacion;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.repository.ParticipacionRepository;
import com.ecoretos.api.repository.UsuarioRepository;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RetoService {

    private final RetoRepository retoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ParticipacionRepository participacionRepository;

    public RetoService(
            RetoRepository retoRepository,
            UsuarioRepository usuarioRepository,
            ParticipacionRepository participacionRepository
    ) {
        this.retoRepository = retoRepository;
        this.usuarioRepository = usuarioRepository;
        this.participacionRepository = participacionRepository;
    }
    public ParticipacionResponse aceptarReto(Integer idReto, Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!"ESTUDIANTE".equals(usuario.getRol())) {
            throw new RuntimeException("Solo los estudiantes pueden aceptar retos");
        }

        Reto reto = retoRepository.findById(idReto)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        if (!Boolean.TRUE.equals(reto.getActivo())) {
            throw new RuntimeException("El reto no está activo");
        }

        if (participacionRepository.existsByUsuario_IdUsuarioAndReto_IdReto(idUsuario, idReto)) {
            throw new RuntimeException("El usuario ya aceptó este reto");
        }

        Participacion participacion = new Participacion();
        participacion.setUsuario(usuario);
        participacion.setReto(reto);
        participacion.setFechaAceptacion(LocalDateTime.now());
        participacion.setEstado("ACEPTADO");

        Participacion guardada = participacionRepository.save(participacion);

        return new ParticipacionResponse(
                guardada.getIdParticipacion(),
                usuario.getIdUsuario(),
                reto.getIdReto(),
                reto.getTitulo(),
                guardada.getEstado(),
                guardada.getFechaAceptacion(),
                "Reto aceptado correctamente"
        );
    }
    public List<RetoResponse> listarRetosActivos() {
        LocalDateTime ahora = LocalDateTime.now();

        List<Reto> retos = retoRepository
                .findByActivoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(ahora, ahora);

        return retos.stream()
                .map(reto -> new RetoResponse(
                        reto.getIdReto(),
                        reto.getTitulo(),
                        reto.getDescripcion(),
                        reto.getPuntos(),
                        reto.getDificultad(),
                        reto.getTipoValidacion(),
                        reto.getFechaInicio(),
                        reto.getFechaFin(),
                        reto.getDuracionHoras()
                ))
                .toList();
    }
}
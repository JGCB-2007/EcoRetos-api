package com.ecoretos.api.service;

import com.ecoretos.api.dto.EvidenciaRequest;
import com.ecoretos.api.dto.EvidenciaResponse;
import com.ecoretos.api.entity.Evidencia;
import com.ecoretos.api.entity.Participacion;
import com.ecoretos.api.repository.EvidenciaRepository;
import com.ecoretos.api.repository.ParticipacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ecoretos.api.dto.EvidenciaAdminResponse;
import com.ecoretos.api.entity.Evidencia;
import java.time.LocalDateTime;
import com.ecoretos.api.dto.AccionEvidenciaResponse;
import com.ecoretos.api.entity.Evidencia;
import com.ecoretos.api.entity.Participacion;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.repository.UsuarioRepository;
import com.ecoretos.api.service.InsigniaService;

@Service
public class EvidenciaService {

    private final EvidenciaRepository evidenciaRepository;
    private final ParticipacionRepository participacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final InsigniaService insigniaService;

    public EvidenciaService(
            EvidenciaRepository evidenciaRepository,
            ParticipacionRepository participacionRepository,
            UsuarioRepository usuarioRepository,
            InsigniaService insigniaService
    ) {
        this.evidenciaRepository = evidenciaRepository;
        this.participacionRepository = participacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.insigniaService = insigniaService;
    }

    public EvidenciaResponse registrarEvidencia(Integer idParticipacion, EvidenciaRequest request) {
        Participacion participacion = participacionRepository.findById(idParticipacion)
                .orElseThrow(() -> new RuntimeException("Participación no encontrada"));

        if (!"ACEPTADO".equals(participacion.getEstado())) {
            throw new RuntimeException("Solo se puede enviar evidencia de retos aceptados");
        }

        if (evidenciaRepository.existsByParticipacion_IdParticipacion(idParticipacion)) {
            throw new RuntimeException("Esta participación ya tiene evidencia registrada");
        }

        Evidencia evidencia = new Evidencia();
        evidencia.setParticipacion(participacion);
        evidencia.setUrlImagen(request.getUrlImagen());
        evidencia.setFechaSubida(LocalDateTime.now());
        evidencia.setEstadoValidacion("PENDIENTE");
        evidencia.setResultadoIA(request.getResultadoIA());
        evidencia.setConfianzaIA(request.getConfianzaIA());
        evidencia.setEtiquetasDetectadas(request.getEtiquetasDetectadas());

        Evidencia guardada = evidenciaRepository.save(evidencia);

        participacion.setEstado("ENVIADO");
        participacionRepository.save(participacion);

        return new EvidenciaResponse(
                guardada.getIdEvidencia(),
                participacion.getIdParticipacion(),
                guardada.getUrlImagen(),
                guardada.getEstadoValidacion(),
                guardada.getResultadoIA(),
                guardada.getConfianzaIA(),
                guardada.getEtiquetasDetectadas(),
                guardada.getFechaSubida(),
                "Evidencia enviada correctamente"
        );
    }
    public List<EvidenciaAdminResponse> listarPendientes() {

        List<Evidencia> evidencias = evidenciaRepository.findByEstadoValidacion("PENDIENTE");

        return evidencias.stream()
                .map(e -> new EvidenciaAdminResponse(
                        e.getIdEvidencia(),
                        e.getParticipacion().getIdParticipacion(),
                        e.getParticipacion().getUsuario().getNombreCompleto(),
                        e.getParticipacion().getReto().getTitulo(),
                        e.getUrlImagen(),
                        e.getEstadoValidacion(),
                        e.getFechaSubida()
                ))
                .toList();
    }
    public AccionEvidenciaResponse aprobarEvidencia(Integer idEvidencia) {

        Evidencia evidencia = evidenciaRepository.findById(idEvidencia)
                .orElseThrow(() -> new RuntimeException("Evidencia no encontrada"));

        if (!"PENDIENTE".equals(evidencia.getEstadoValidacion())) {
            throw new RuntimeException("La evidencia ya fue procesada");
        }

        evidencia.setEstadoValidacion("APROBADA");

        Participacion participacion = evidencia.getParticipacion();
        participacion.setEstado("APROBADO");

        Usuario usuario = participacion.getUsuario();
        Integer puntos = participacion.getReto().getPuntos();

        usuario.setPuntosTotales(usuario.getPuntosTotales() + puntos);

        evidenciaRepository.save(evidencia);
        participacionRepository.save(participacion);
        usuarioRepository.save(usuario);

        insigniaService.asignarInsigniasSiCorresponde(usuario);

        return new AccionEvidenciaResponse(
                evidencia.getIdEvidencia(),
                evidencia.getEstadoValidacion(),
                "Evidencia aprobada y puntos asignados"
        );
    }
    public AccionEvidenciaResponse rechazarEvidencia(Integer idEvidencia) {

        Evidencia evidencia = evidenciaRepository.findById(idEvidencia)
                .orElseThrow(() -> new RuntimeException("Evidencia no encontrada"));

        if (!"PENDIENTE".equals(evidencia.getEstadoValidacion())) {
            throw new RuntimeException("La evidencia ya fue procesada");
        }

        evidencia.setEstadoValidacion("RECHAZADA");

        Participacion participacion = evidencia.getParticipacion();
        participacion.setEstado("RECHAZADO");

        evidenciaRepository.save(evidencia);
        participacionRepository.save(participacion);

        return new AccionEvidenciaResponse(
                evidencia.getIdEvidencia(),
                evidencia.getEstadoValidacion(),
                "Evidencia rechazada"
        );
    }

}
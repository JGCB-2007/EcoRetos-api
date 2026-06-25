package com.ecoretos.api.service;


import com.ecoretos.api.dto.CrearRetoRequest;
import com.ecoretos.api.dto.ParticipacionResponse;
import com.ecoretos.api.dto.RetoResponse;
import com.ecoretos.api.entity.Participacion;
import com.ecoretos.api.entity.Reto;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.repository.ParticipacionRepository;
import com.ecoretos.api.repository.RetoRepository;
import com.ecoretos.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

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
    public RetoResponse crearRetoAdmin(CrearRetoRequest request) {
        Usuario admin = usuarioRepository.findById(request.getCreadoPor())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        if (!"ADMINISTRADOR".equals(admin.getRol())) {
            throw new RuntimeException("Solo un administrador puede crear retos");
        }

        if (request.getPuntos() <= 0) {
            throw new RuntimeException("Los puntos deben ser mayores que 0");
        }

        String dificultad = request.getDificultad().toUpperCase();
        String tipoValidacion = request.getTipoValidacion().toUpperCase();

        if (!dificultad.equals("FACIL") && !dificultad.equals("MEDIA") && !dificultad.equals("ALTA")) {
            throw new RuntimeException("Dificultad inválida. Use FACIL, MEDIA o ALTA");
        }

        if (!tipoValidacion.equals("MANUAL") && !tipoValidacion.equals("IA_APOYO") && !tipoValidacion.equals("HIBRIDA")) {
            throw new RuntimeException("Tipo de validación inválido. Use MANUAL, IA_APOYO o HIBRIDA");
        }

        int duracion = request.getDuracionHoras() != null ? request.getDuracionHoras() : 24;

        LocalDateTime ahora = LocalDateTime.now();

        Reto reto = new Reto();
        reto.setTitulo(request.getTitulo());
        reto.setDescripcion(request.getDescripcion());
        reto.setPuntos(request.getPuntos());
        reto.setDificultad(dificultad);
        reto.setTipoValidacion(tipoValidacion);
        reto.setActivo(true);
        reto.setFechaCreacion(ahora);
        reto.setFechaInicio(ahora);
        reto.setDuracionHoras(duracion);
        reto.setFechaFin(ahora.plusHours(duracion));
        reto.setCreadoPor(admin);

        Reto guardado = retoRepository.save(reto);

        return new RetoResponse(
                guardado.getIdReto(),
                guardado.getTitulo(),
                guardado.getDescripcion(),
                guardado.getPuntos(),
                guardado.getDificultad(),
                guardado.getTipoValidacion(),
                guardado.getFechaInicio(),
                guardado.getFechaFin(),
                guardado.getDuracionHoras()
        );
    }
    public RetoResponse editarRetoAdmin(Integer idReto, CrearRetoRequest request) {
        Usuario admin = usuarioRepository.findById(request.getCreadoPor())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        if (!"ADMINISTRADOR".equals(admin.getRol())) {
            throw new RuntimeException("Solo un administrador puede editar retos");
        }

        Reto reto = retoRepository.findById(idReto)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        if (request.getPuntos() <= 0) {
            throw new RuntimeException("Los puntos deben ser mayores que 0");
        }

        String dificultad = request.getDificultad().toUpperCase();
        String tipoValidacion = request.getTipoValidacion().toUpperCase();

        if (!dificultad.equals("FACIL") && !dificultad.equals("MEDIA") && !dificultad.equals("ALTA")) {
            throw new RuntimeException("Dificultad inválida. Use FACIL, MEDIA o ALTA");
        }

        if (!tipoValidacion.equals("MANUAL") && !tipoValidacion.equals("IA_APOYO") && !tipoValidacion.equals("HIBRIDA")) {
            throw new RuntimeException("Tipo de validación inválido. Use MANUAL, IA_APOYO o HIBRIDA");
        }

        int duracion = request.getDuracionHoras() != null ? request.getDuracionHoras() : 24;

        LocalDateTime fechaInicio = reto.getFechaInicio() != null
                ? reto.getFechaInicio()
                : LocalDateTime.now();

        reto.setTitulo(request.getTitulo());
        reto.setDescripcion(request.getDescripcion());
        reto.setPuntos(request.getPuntos());
        reto.setDificultad(dificultad);
        reto.setTipoValidacion(tipoValidacion);
        reto.setDuracionHoras(duracion);
        reto.setFechaInicio(fechaInicio);
        reto.setFechaFin(fechaInicio.plusHours(duracion));

        Reto actualizado = retoRepository.save(reto);

        return new RetoResponse(
                actualizado.getIdReto(),
                actualizado.getTitulo(),
                actualizado.getDescripcion(),
                actualizado.getPuntos(),
                actualizado.getDificultad(),
                actualizado.getTipoValidacion(),
                actualizado.getFechaInicio(),
                actualizado.getFechaFin(),
                actualizado.getDuracionHoras()
        );
    }
    public RetoResponse desactivarRetoAdmin(Integer idReto) {
        Reto reto = retoRepository.findById(idReto)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        reto.setActivo(false);

        Reto actualizado = retoRepository.save(reto);

        return new RetoResponse(
                actualizado.getIdReto(),
                actualizado.getTitulo(),
                actualizado.getDescripcion(),
                actualizado.getPuntos(),
                actualizado.getDificultad(),
                actualizado.getTipoValidacion(),
                actualizado.getFechaInicio(),
                actualizado.getFechaFin(),
                actualizado.getDuracionHoras()
        );
    }
}
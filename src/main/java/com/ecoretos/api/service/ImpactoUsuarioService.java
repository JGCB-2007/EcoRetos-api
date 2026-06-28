package com.ecoretos.api.service;

import com.ecoretos.api.dto.ImpactoUsuarioResponse;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.repository.ParticipacionRepository;
import com.ecoretos.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImpactoUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ParticipacionRepository participacionRepository;

    public ImpactoUsuarioService(
            UsuarioRepository usuarioRepository,
            ParticipacionRepository participacionRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.participacionRepository = participacionRepository;
    }

    public ImpactoUsuarioResponse obtenerImpacto(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Long retosCompletados = participacionRepository
                .countByUsuario_IdUsuarioAndEstado(idUsuario, "APROBADO");

        LocalDate hoy = LocalDate.now();

        LocalDate inicioSemana = hoy.with(DayOfWeek.MONDAY);
        LocalDate finSemana = hoy.with(DayOfWeek.SUNDAY);

        Long retosCompletadosSemana = participacionRepository
                .countByUsuario_IdUsuarioAndEstadoAndFechaAprobacionBetween(
                        idUsuario,
                        "APROBADO",
                        inicioSemana.atStartOfDay(),
                        finSemana.atTime(23, 59, 59)
                );

        Integer rachaDias = calcularRacha(idUsuario);
        Integer posicionRanking = calcularPosicionRanking(idUsuario);

        return new ImpactoUsuarioResponse(
                usuario.getPuntosTotales(),
                retosCompletados,
                retosCompletadosSemana,
                rachaDias,
                posicionRanking
        );
    }

    private Integer calcularRacha(Integer idUsuario) {
        List<LocalDateTime> fechas = participacionRepository
                .obtenerFechasAprobacionUsuario(idUsuario);

        if (fechas.isEmpty()) {
            return 0;
        }

        List<LocalDate> diasAprobados = fechas.stream()
                .map(LocalDateTime::toLocalDate)
                .distinct()
                .toList();

        LocalDate diaActual = LocalDate.now();
        int racha = 0;

        while (diasAprobados.contains(diaActual)) {
            racha++;
            diaActual = diaActual.minusDays(1);
        }

        return racha;
    }

    private Integer calcularPosicionRanking(Integer idUsuario) {
        List<Usuario> usuarios = usuarioRepository
                .findByRolAndActivoTrueOrderByPuntosTotalesDescNombreCompletoAsc("ESTUDIANTE");

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario().equals(idUsuario)) {
                return i + 1;
            }
        }

        return 0;
    }
}
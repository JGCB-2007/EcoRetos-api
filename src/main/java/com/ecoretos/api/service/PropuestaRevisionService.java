package com.ecoretos.api.service;

import com.ecoretos.api.dto.PropuestaResponse;
import com.ecoretos.api.dto.RevisionPropuestaRequest;
import com.ecoretos.api.entity.Reto;
import com.ecoretos.api.entity.RetoPropuesto;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.mapper.PropuestaMapper;
import com.ecoretos.api.repository.RetoPropuestoRepository;
import com.ecoretos.api.repository.RetoRepository;
import com.ecoretos.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PropuestaRevisionService {

    private final RetoPropuestoRepository propuestaRepository;
    private final RetoRepository retoRepository;
    private final UsuarioRepository usuarioRepository;

    public PropuestaRevisionService(
            RetoPropuestoRepository propuestaRepository,
            RetoRepository retoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.propuestaRepository = propuestaRepository;
        this.retoRepository = retoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public PropuestaResponse aprobar(
            Integer idPropuesta,
            RevisionPropuestaRequest request
    ) {
        Usuario admin = usuarioRepository.findById(request.getIdAdministrador())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        if (!"ADMINISTRADOR".equals(admin.getRol())) {
            throw new RuntimeException("El usuario no es administrador");
        }

        RetoPropuesto propuesta = propuestaRepository.findById(idPropuesta)
                .orElseThrow(() -> new RuntimeException("Propuesta no encontrada"));

        if (!propuesta.getEstado().equals("PENDIENTE")) {
            throw new RuntimeException("La propuesta ya fue revisada");
        }

        Reto reto = new Reto();

        reto.setTitulo(propuesta.getTitulo());
        reto.setDescripcion(propuesta.getDescripcion());
        reto.setPuntos(request.getPuntos());
        reto.setDificultad(request.getDificultad());
        reto.setTipoValidacion(request.getTipoValidacion());

        reto.setActivo(true);

        LocalDateTime ahora = LocalDateTime.now();

        reto.setFechaCreacion(ahora);
        reto.setFechaInicio(ahora);

        Integer horas = request.getDuracionHoras() == null
                ? 24
                : request.getDuracionHoras();

        reto.setDuracionHoras(horas);
        reto.setFechaFin(ahora.plusHours(horas));

        reto.setCreadoPor(admin);

        retoRepository.save(reto);

        propuesta.setEstado("APROBADA");
        propuesta.setObservacionAdmin(request.getObservacionAdmin());

        propuestaRepository.save(propuesta);

        return PropuestaMapper.toResponse(propuesta);
    }

    @Transactional
    public PropuestaResponse rechazar(
            Integer idPropuesta,
            RevisionPropuestaRequest request
    ) {

        RetoPropuesto propuesta = propuestaRepository.findById(idPropuesta)
                .orElseThrow(() -> new RuntimeException("Propuesta no encontrada"));

        propuesta.setEstado("RECHAZADA");
        propuesta.setObservacionAdmin(request.getObservacionAdmin());

        propuestaRepository.save(propuesta);

        return PropuestaMapper.toResponse(propuesta);
    }

}
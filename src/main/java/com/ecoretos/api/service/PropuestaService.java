package com.ecoretos.api.service;

import com.ecoretos.api.dto.CrearPropuestaRequest;
import com.ecoretos.api.dto.PropuestaResponse;
import com.ecoretos.api.entity.RetoPropuesto;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.mapper.PropuestaMapper;
import com.ecoretos.api.repository.RetoPropuestoRepository;
import com.ecoretos.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PropuestaService {

    private final RetoPropuestoRepository retoPropuestoRepository;
    private final UsuarioRepository usuarioRepository;

    public PropuestaService(
            RetoPropuestoRepository retoPropuestoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.retoPropuestoRepository = retoPropuestoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PropuestaResponse crearPropuesta(CrearPropuestaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!"ESTUDIANTE".equals(usuario.getRol())) {
            throw new RuntimeException("Solo estudiantes pueden proponer retos");
        }

        RetoPropuesto propuesta = new RetoPropuesto();
        propuesta.setUsuario(usuario);
        propuesta.setTitulo(request.getTitulo());
        propuesta.setDescripcion(request.getDescripcion());
        propuesta.setCategoria(request.getCategoria());
        propuesta.setEstado("PENDIENTE");
        propuesta.setFechaPropuesta(LocalDateTime.now());

        RetoPropuesto guardada = retoPropuestoRepository.save(propuesta);

        return PropuestaMapper.toResponse(guardada);
    }

    public List<PropuestaResponse> listarPendientes() {
        return retoPropuestoRepository.findByEstadoOrderByFechaPropuestaDesc("PENDIENTE")
                .stream()
                .map(PropuestaMapper::toResponse)
                .toList();
    }

    public List<PropuestaResponse> listarMisPropuestas(Integer idUsuario) {
        return retoPropuestoRepository.findByUsuario_IdUsuarioOrderByFechaPropuestaDesc(idUsuario)
                .stream()
                .map(PropuestaMapper::toResponse)
                .toList();
    }
}
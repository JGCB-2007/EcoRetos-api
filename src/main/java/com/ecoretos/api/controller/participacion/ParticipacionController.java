package com.ecoretos.api.controller.participacion;

import com.ecoretos.api.dto.MisParticipacionesResponse;
import com.ecoretos.api.service.ParticipacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ParticipacionController {

    private final ParticipacionService participacionService;

    public ParticipacionController(ParticipacionService participacionService) {
        this.participacionService = participacionService;
    }

    @GetMapping("/{idUsuario}/participaciones")
    public ResponseEntity<List<MisParticipacionesResponse>> listarParticipaciones(
            @PathVariable Integer idUsuario
    ) {
        return ResponseEntity.ok(participacionService.listarPorUsuario(idUsuario));
    }
}
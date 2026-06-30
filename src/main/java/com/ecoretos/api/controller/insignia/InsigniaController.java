package com.ecoretos.api.controller.insignia;

import com.ecoretos.api.dto.InsigniaResponse;
import com.ecoretos.api.service.InsigniaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insignias")
public class InsigniaController {

    private final InsigniaService insigniaService;

    public InsigniaController(InsigniaService insigniaService) {
        this.insigniaService = insigniaService;
    }

    @GetMapping
    public ResponseEntity<List<InsigniaResponse>> listarInsignias() {
        return ResponseEntity.ok(insigniaService.listarInsigniasActivas());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<InsigniaResponse>> listarInsigniasUsuario(
            @PathVariable Integer idUsuario
    ) {
        return ResponseEntity.ok(insigniaService.listarInsigniasPorUsuario(idUsuario));
    }
}
package com.ecoretos.api.controller.admin;

import com.ecoretos.api.dto.CrearInsigniaRequest;
import com.ecoretos.api.dto.InsigniaResponse;
import com.ecoretos.api.service.InsigniaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/insignias")
public class AdminInsigniaController {

    private final InsigniaService insigniaService;

    public AdminInsigniaController(InsigniaService insigniaService) {
        this.insigniaService = insigniaService;
    }

    @PostMapping
    public ResponseEntity<InsigniaResponse> crearInsignia(
            @Valid @RequestBody CrearInsigniaRequest request
    ) {
        return ResponseEntity.ok(insigniaService.crearInsignia(request));
    }
}
package com.ecoretos.api.controller;

import com.ecoretos.api.dto.RankingResponse;
import com.ecoretos.api.service.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping
    public ResponseEntity<List<RankingResponse>> obtenerRanking() {
        return ResponseEntity.ok(rankingService.obtenerRanking());
    }
}
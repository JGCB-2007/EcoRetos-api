package com.ecoretos.api.controller;

import com.ecoretos.api.dto.AdminDashboardResponse;
import com.ecoretos.api.service.AdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/dashboard")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    public AdminDashboardController(AdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping
    public ResponseEntity<AdminDashboardResponse> obtenerDashboard() {
        return ResponseEntity.ok(adminDashboardService.obtenerDashboard());
    }
}
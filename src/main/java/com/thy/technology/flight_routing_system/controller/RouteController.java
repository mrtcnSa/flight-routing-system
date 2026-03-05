package com.thy.technology.flight_routing_system.controller;

import com.thy.technology.flight_routing_system.dto.response.RouteResponse;
import com.thy.technology.flight_routing_system.service.RouteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/routes")
@SecurityRequirement(name = "basicAuth")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // Arama İşlemi: GET metodu kullanıyoruz ve URL üzerinden veri alıyoruz
    @GetMapping("/search")
    public ResponseEntity<List<RouteResponse>> searchRoutes(
            @RequestParam Long originId,
            @RequestParam Long destinationId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        return ResponseEntity.ok(routeService.searchRoutes(originId, destinationId, date));
    }
}
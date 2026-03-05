package com.thy.technology.flight_routing_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Yeni DTO'larımızı import ediyoruz
import com.thy.technology.flight_routing_system.dto.request.LocationRequest;
import com.thy.technology.flight_routing_system.dto.response.LocationResponse;
import com.thy.technology.flight_routing_system.service.LocationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/locations")
@SecurityRequirement(name = "basicAuth")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // CREATE: Dışarıdan 'Request' gelir, dışarıya 'Response' döner
    @PostMapping
    public ResponseEntity<LocationResponse> create(@Valid @RequestBody LocationRequest request) {
        return new ResponseEntity<>(locationService.createLocation(request), HttpStatus.CREATED);
    }

    // READ: Dışarıya 'Response' listesi döner
    @GetMapping
    public ResponseEntity<List<LocationResponse>> getAll() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    // UPDATE: Dışarıdan 'Request' gelir, dışarıya 'Response' döner
    @PutMapping("/{id}")
    public ResponseEntity<LocationResponse> update(@PathVariable Long id, @Valid @RequestBody LocationRequest request) {
        return ResponseEntity.ok(locationService.updateLocation(id, request));
    }

    // DELETE: Sadece ID alır, geriye veri dönmez (204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
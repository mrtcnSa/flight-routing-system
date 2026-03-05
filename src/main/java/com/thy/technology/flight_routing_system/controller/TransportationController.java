package com.thy.technology.flight_routing_system.controller;

import com.thy.technology.flight_routing_system.dto.request.TransportationRequest;
import com.thy.technology.flight_routing_system.dto.response.TransportationResponse;
import com.thy.technology.flight_routing_system.service.TransportationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/transportations")
@SecurityRequirement(name = "basicAuth")
public class TransportationController {

    private final TransportationService transportationService;

    public TransportationController(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    // CREATE: Dışarıdan 'Request' alır, validasyon yapar, 'Response' döner
    @PostMapping
    public ResponseEntity<TransportationResponse> create(@Valid @RequestBody TransportationRequest request) {
        return new ResponseEntity<>(transportationService.createTransportation(request), HttpStatus.CREATED);
    }

    // READ: Dışarıya 'Response' listesi döner
    @GetMapping
    public ResponseEntity<List<TransportationResponse>> getAll() {
        return ResponseEntity.ok(transportationService.getAllTransportations());
    }

    // UPDATE: Dışarıdan 'Request' alır, validasyon yapar, 'Response' döner
    @PutMapping("/{id}")
    public ResponseEntity<TransportationResponse> update(@PathVariable Long id, @Valid @RequestBody TransportationRequest request) {
        return ResponseEntity.ok(transportationService.updateTransportation(id, request));
    }

    // DELETE: Parametre olarak sadece ID alır
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transportationService.deleteTransportation(id);
        return ResponseEntity.noContent().build();
    }
}
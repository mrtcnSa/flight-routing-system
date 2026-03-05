package com.thy.technology.flight_routing_system.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

// Yeni Response DTO'muz
import com.thy.technology.flight_routing_system.dto.response.RouteResponse;
import com.thy.technology.flight_routing_system.model.Transportation;
import com.thy.technology.flight_routing_system.model.TransportationType;
import com.thy.technology.flight_routing_system.repository.TransportationRepository;
import com.thy.technology.flight_routing_system.mapper.TransportationMapper;

@Slf4j
@Service
public class RouteService {

    private final TransportationRepository transportationRepository;
    private final TransportationMapper transportationMapper;

    public RouteService(TransportationRepository transportationRepository, TransportationMapper transportationMapper) {
        this.transportationRepository = transportationRepository;
        this.transportationMapper = transportationMapper;
    }

    @Cacheable(value = "routes", key = "#originId + '-' + #destinationId + '-' + #date.toString()")
    public List<RouteResponse> searchRoutes(Long originId, Long destinationId, LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        List<RouteResponse> validRoutes = new ArrayList<>();

        List<Transportation> allAvailable = transportationRepository.findAll().stream()
                .filter(t -> t.getOperatingDays() != null && t.getOperatingDays().contains(dayOfWeek))
                .toList();

        log.info("Arama: Origin={}, Dest={}, Mevcut Araç={}", originId, destinationId, allAvailable.size());

        List<Transportation> flights = allAvailable.stream().filter(t -> t.getType() == TransportationType.FLIGHT).toList();
        List<Transportation> others = allAvailable.stream().filter(t -> t.getType() != TransportationType.FLIGHT).toList();

        for (Transportation flight : flights) {
            
            // SADECE UÇUŞ
            if (flight.getOriginLocation().getId().equals(originId) && flight.getDestinationLocation().getId().equals(destinationId)) {
                validRoutes.add(new RouteResponse(List.of(transportationMapper.toResponse(flight))));
            }

            // TRANSFER + UÇUŞ
            for (Transportation pre : others) {
                if (pre.getOriginLocation().getId().equals(originId) && 
                    pre.getDestinationLocation().getId().equals(flight.getOriginLocation().getId()) &&
                    flight.getDestinationLocation().getId().equals(destinationId)) {
                    validRoutes.add(new RouteResponse(List.of(transportationMapper.toResponse(pre), transportationMapper.toResponse(flight))));
                }
            }

            // UÇUŞ + TRANSFER
            for (Transportation post : others) {
                if (flight.getOriginLocation().getId().equals(originId) && 
                    flight.getDestinationLocation().getId().equals(post.getOriginLocation().getId()) &&
                    post.getDestinationLocation().getId().equals(destinationId)) {
                    validRoutes.add(new RouteResponse(List.of(transportationMapper.toResponse(flight), transportationMapper.toResponse(post))));
                }
            }

            // TRANSFER + UÇUŞ + TRANSFER
            for (Transportation pre : others) {
                for (Transportation post : others) {
                    if (pre.getOriginLocation().getId().equals(originId) && 
                        pre.getDestinationLocation().getId().equals(flight.getOriginLocation().getId()) &&
                        flight.getDestinationLocation().getId().equals(post.getOriginLocation().getId()) &&
                        post.getDestinationLocation().getId().equals(destinationId)) {
                        validRoutes.add(new RouteResponse(List.of(transportationMapper.toResponse(pre), transportationMapper.toResponse(flight), transportationMapper.toResponse(post))));
                    }
                }
            }
        }

        return validRoutes.stream().distinct().collect(Collectors.toList());
    }
}
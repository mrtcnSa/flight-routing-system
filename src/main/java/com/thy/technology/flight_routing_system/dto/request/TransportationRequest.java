package com.thy.technology.flight_routing_system.dto.request;

import com.thy.technology.flight_routing_system.model.TransportationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class TransportationRequest {

    @NotNull(message = "Kalkış noktası ID'si (originLocationId) boş olamaz")
    private Long originLocationId;

    @NotNull(message = "Varış noktası ID'si (destinationLocationId) boş olamaz")
    private Long destinationLocationId;

    @NotNull(message = "Ulaşım tipi (FLIGHT, BUS, UBER) boş olamaz")
    private TransportationType type;

    @NotEmpty(message = "Çalışma günleri listesi boş olamaz")
    private List<Integer> operatingDays;
}
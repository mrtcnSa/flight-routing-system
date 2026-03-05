package com.thy.technology.flight_routing_system.dto.response;

import com.thy.technology.flight_routing_system.model.TransportationType;
import lombok.Data;
import java.util.List;

@Data
public class TransportationResponse {
    private Long id;
    private Long originLocationId;
    private Long destinationLocationId;
    private TransportationType type;
    private List<Integer> operatingDays;
}
package com.thy.technology.flight_routing_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {
    // Rota, birden fazla aktarma (Transportation) içerebilir
    private List<TransportationResponse> transportations; 
}
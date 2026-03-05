package com.thy.technology.flight_routing_system.dto.response;

import lombok.Data;

@Data
public class LocationResponse {
    private Long id;  
    private String name;
    private String country;
    private String city;
    private String locationCode;
}
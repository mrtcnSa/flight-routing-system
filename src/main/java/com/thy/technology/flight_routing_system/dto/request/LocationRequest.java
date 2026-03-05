package com.thy.technology.flight_routing_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LocationRequest {
    
    @NotBlank(message = "Lokasyon adı boş olamaz")
    private String name;
    
    @NotBlank(message = "Ülke boş olamaz")
    private String country;
    
    @NotBlank(message = "Şehir boş olamaz")
    private String city;
    
    @NotBlank(message = "Lokasyon kodu (IATA) boş olamaz")
	@Size(min = 3, max = 5, message = "Kod 3-5 karakter arası olmalıdır")
    private String locationCode;
}
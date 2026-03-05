package com.thy.technology.flight_routing_system.exception;

// Bu hata bir yer bulunamadığında (404) fırlatılacak
public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String message) {
        super(message);
    }
}
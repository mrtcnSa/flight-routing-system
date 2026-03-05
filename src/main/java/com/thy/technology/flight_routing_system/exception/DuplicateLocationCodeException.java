package com.thy.technology.flight_routing_system.exception;

// Bu hata aynı kodla (IATA) kayıt girilmek istendiğinde (409) fırlatılacak
public class DuplicateLocationCodeException extends RuntimeException {
    public DuplicateLocationCodeException(String message) {
        super(message);
    }
}
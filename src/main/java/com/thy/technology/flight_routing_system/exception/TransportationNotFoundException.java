package com.thy.technology.flight_routing_system.exception;

public class TransportationNotFoundException extends RuntimeException {
    public TransportationNotFoundException(String message) {
        super(message);
    }
}
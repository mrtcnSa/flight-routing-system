package com.thy.technology.flight_routing_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LocationNotFoundException.class)
	public ResponseEntity<Object> handleNotFound(LocationNotFoundException ex) {
		return createResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(DuplicateLocationCodeException.class)
	public ResponseEntity<Object> handleConflict(DuplicateLocationCodeException ex) {
		return createResponse(HttpStatus.CONFLICT, ex.getMessage());
	}

	@ExceptionHandler(TransportationNotFoundException.class)
	public ResponseEntity<Object> handleTransportationNotFound(TransportationNotFoundException ex) {
		return createResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	private ResponseEntity<Object> createResponse(HttpStatus status, String message) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		body.put("error", status.getReasonPhrase());
		body.put("message", message);
		return new ResponseEntity<>(body, status);
	}
}
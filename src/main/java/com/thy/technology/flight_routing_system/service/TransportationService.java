package com.thy.technology.flight_routing_system.service;

import com.thy.technology.flight_routing_system.dto.request.TransportationRequest;
import com.thy.technology.flight_routing_system.dto.response.TransportationResponse;
import com.thy.technology.flight_routing_system.exception.LocationNotFoundException;
import com.thy.technology.flight_routing_system.exception.TransportationNotFoundException;
import com.thy.technology.flight_routing_system.model.Location;
import com.thy.technology.flight_routing_system.model.Transportation;
import com.thy.technology.flight_routing_system.repository.LocationRepository;
import com.thy.technology.flight_routing_system.repository.TransportationRepository;
import com.thy.technology.flight_routing_system.mapper.TransportationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportationService {

	private final TransportationRepository transportationRepository;
	private final LocationRepository locationRepository;
	private final TransportationMapper transportationMapper;

	public TransportationService(TransportationRepository transportationRepository,
			LocationRepository locationRepository, TransportationMapper transportationMapper) {
		this.transportationRepository = transportationRepository;
		this.locationRepository = locationRepository;
		this.transportationMapper = transportationMapper;
	}

	// CREATE
	public TransportationResponse createTransportation(TransportationRequest request) {
		Transportation transportation = transportationMapper.toEntity(request);
		assignLocations(transportation, request);

		Transportation saved = transportationRepository.save(transportation);
		return transportationMapper.toResponse(saved);
	}

	// READ (ALL)
	public List<TransportationResponse> getAllTransportations() {
		return transportationRepository.findAll().stream().map(transportationMapper::toResponse)
				.collect(Collectors.toList());
	}

	// UPDATE
	public TransportationResponse updateTransportation(Long id, TransportationRequest request) {
		Transportation transportation = transportationRepository.findById(id)
				.orElseThrow(() -> new TransportationNotFoundException(id + " ID'li araç/uçuş bulunamadı!"));  

		transportationMapper.updateEntityFromRequest(request, transportation);
		assignLocations(transportation, request);

		Transportation saved = transportationRepository.save(transportation);
		return transportationMapper.toResponse(saved);
	}

	// DELETE
	public void deleteTransportation(Long id) {
		if (!transportationRepository.existsById(id)) {
			throw new TransportationNotFoundException(id + " ID'li silinecek kayıt bulunamadı!");  
		}
		transportationRepository.deleteById(id);
	}

	// --- İŞ MANTIĞI YARDIMCISI ---
	private void assignLocations(Transportation t, TransportationRequest request) {
		Location origin = locationRepository.findById(request.getOriginLocationId()).orElseThrow(
				() -> new LocationNotFoundException("Kalkış noktası bulunamadı! ID: " + request.getOriginLocationId())); // GÜNCELLENDİ

		Location destination = locationRepository.findById(request.getDestinationLocationId())
				.orElseThrow(() -> new LocationNotFoundException(
						"Varış noktası bulunamadı! ID: " + request.getDestinationLocationId())); // GÜNCELLENDİ

		t.setOriginLocation(origin);
		t.setDestinationLocation(destination);
	}
}
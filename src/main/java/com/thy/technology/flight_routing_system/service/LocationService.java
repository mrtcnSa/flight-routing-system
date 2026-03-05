package com.thy.technology.flight_routing_system.service;

import org.springframework.stereotype.Service;
import com.thy.technology.flight_routing_system.dto.request.LocationRequest;
import com.thy.technology.flight_routing_system.dto.response.LocationResponse;
import com.thy.technology.flight_routing_system.exception.LocationNotFoundException;
import com.thy.technology.flight_routing_system.exception.DuplicateLocationCodeException;
import com.thy.technology.flight_routing_system.model.Location;
import com.thy.technology.flight_routing_system.repository.LocationRepository;
import com.thy.technology.flight_routing_system.mapper.LocationMapper;
import com.thy.technology.flight_routing_system.util.StringUtil; // IMPORT EKLE
import com.thy.technology.flight_routing_system.util.AppConstants; // IMPORT EKLE

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

	private final LocationRepository locationRepository;
	private final LocationMapper locationMapper;

	public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
		this.locationRepository = locationRepository;
		this.locationMapper = locationMapper;
	}

	// CREATE
	public LocationResponse createLocation(LocationRequest request) {
		// Kontrol etmeden önce formatlayalım (Örn: " ist " -> "IST")
		String formattedCode = StringUtil.formatLocationCode(request.getLocationCode());  

		if (locationRepository.existsByLocationCode(formattedCode)) {
			throw new DuplicateLocationCodeException("Bu lokasyon kodu zaten mevcut: " + formattedCode);
		}

		Location location = locationMapper.toEntity(request);
		location.setLocationCode(formattedCode); 

		Location savedLocation = locationRepository.save(location);
		return locationMapper.toResponse(savedLocation);
	}

	// READ (ALL)
	public List<LocationResponse> getAllLocations() {
		return locationRepository.findAll().stream().map(locationMapper::toResponse).collect(Collectors.toList());
	}

	// UPDATE
	public LocationResponse updateLocation(Long id, LocationRequest request) {
		Location location = locationRepository.findById(id).orElseThrow(
				() -> new LocationNotFoundException(String.format(AppConstants.LOCATION_NOT_FOUND_MSG, id)));

		String formattedCode = StringUtil.formatLocationCode(request.getLocationCode()); // YENİ

		if (!location.getLocationCode().equals(formattedCode)
				&& locationRepository.existsByLocationCode(formattedCode)) {
			throw new DuplicateLocationCodeException(
					"Bu yeni lokasyon kodu (" + formattedCode + ") başka bir kayıtta mevcut!");
		}

		locationMapper.updateEntityFromRequest(request, location);
		location.setLocationCode(formattedCode); // YENİ: Güncellemede de formatlıyoruz

		Location updatedLocation = locationRepository.save(location);
		return locationMapper.toResponse(updatedLocation);
	}

	// DELETE
	public void deleteLocation(Long id) {
		if (!locationRepository.existsById(id)) {
			throw new LocationNotFoundException(String.format(AppConstants.LOCATION_NOT_FOUND_MSG, id)); // YENİ: Sabit
																											// kullanıldı
		}
		locationRepository.deleteById(id);
	}
}
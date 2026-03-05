package com.thy.technology.flight_routing_system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Yeni DTO ve Mapper importlarımız
import com.thy.technology.flight_routing_system.dto.response.RouteResponse;
import com.thy.technology.flight_routing_system.dto.response.TransportationResponse;
import com.thy.technology.flight_routing_system.mapper.TransportationMapper;
import com.thy.technology.flight_routing_system.model.Location;
import com.thy.technology.flight_routing_system.model.Transportation;
import com.thy.technology.flight_routing_system.model.TransportationType;
import com.thy.technology.flight_routing_system.repository.TransportationRepository;

// SENIOR DOKUNUŞU: @BeforeEach yerine modern JUnit 5 Mockito eklentisi
@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

	@Mock
	private TransportationRepository transportationRepository; // Sahte veritabanı

	@Mock
	private TransportationMapper transportationMapper; // YENİ: Sahte Mapper eklendi

	@InjectMocks
	private RouteService routeService; // Test edilecek asıl servis

	@Test
	void testSearchRoutes_ShouldReturnRoute_WhenValidFlightExists() {
		// GIVEN: Test verilerini hazırla
		Location loc1 = new Location();
		loc1.setId(1L);
		Location loc2 = new Location();
		loc2.setId(2L);

		Transportation flight = new Transportation();
		flight.setId(1L);
		flight.setOriginLocation(loc1);
		flight.setDestinationLocation(loc2);
		flight.setType(TransportationType.FLIGHT);
		flight.setOperatingDays(Arrays.asList(1, 2, 3, 4, 5, 6, 7)); // Her gün

		LocalDate testDate = LocalDate.of(2026, 3, 3); // Salı (2. gün)

		// Mapper'ın döneceği sahte Response nesnesini hazırla
		TransportationResponse flightResponse = new TransportationResponse();
		flightResponse.setId(1L);
		flightResponse.setType(TransportationType.FLIGHT);

		// STUBBING: Sahte nesnelere ne yapacaklarını söyle
		when(transportationRepository.findAll()).thenReturn(List.of(flight));
		// Mapper çağrıldığında bizim sahte response'u dönmeli!
		when(transportationMapper.toResponse(flight)).thenReturn(flightResponse);

		// WHEN: Servisi çağır (Eski RouteDto yerine RouteResponse)
		List<RouteResponse> results = routeService.searchRoutes(1L, 2L, testDate);

		// THEN: Sonuçları doğrula
		assertFalse(results.isEmpty(), "Rota listesi boş olmamalıydı");
		assertEquals(1, results.get(0).getTransportations().size());
		assertEquals(TransportationType.FLIGHT, results.get(0).getTransportations().get(0).getType());
	}
}
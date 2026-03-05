package com.thy.technology.flight_routing_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thy.technology.flight_routing_system.model.Transportation;
import com.thy.technology.flight_routing_system.model.TransportationType;

import java.util.List;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Long> {
	// Kalkış noktasına göre ulaşımları bulmak için özel metod
	List<Transportation> findByOriginLocationId(Long originId);

	// 1. N+1 Problemini Çözen Performans Sorgusu (JOIN FETCH)
	@Query("SELECT t FROM Transportation t JOIN FETCH t.originLocation JOIN FETCH t.destinationLocation WHERE t.type = :type")
	List<Transportation> findByTypeWithLocations(@Param("type") TransportationType type);

	// 2. ElementCollection (Liste) İçinde Veritabanı Seviyesinde Filtreleme
	@Query("SELECT t FROM Transportation t JOIN t.operatingDays d WHERE t.originLocation.id = :originId AND d = :dayOfWeek")
	List<Transportation> findByOriginAndDay(@Param("originId") Long originId, @Param("dayOfWeek") Integer dayOfWeek);
}
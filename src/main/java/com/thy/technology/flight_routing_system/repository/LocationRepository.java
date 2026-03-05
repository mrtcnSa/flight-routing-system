package com.thy.technology.flight_routing_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.thy.technology.flight_routing_system.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	boolean existsByLocationCode(String locationCode);

	// 1. Şehir veya Havalimanı Adına Göre Esnek Arama (Büyük/Küçük Harf Duyarsız)
	@Query("SELECT l FROM Location l WHERE LOWER(l.city) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(l.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Location> searchByCityOrName(@Param("keyword") String keyword);

	// 2. Sadece İhtiyaç Olan Sütunu Çekme (Projection / Performans Optimizasyonu)
	@Query("SELECT l.locationCode FROM Location l WHERE l.country = :country")
	List<String> findLocationCodesByCountry(@Param("country") String country);

}
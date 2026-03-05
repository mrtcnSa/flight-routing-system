package com.thy.technology.flight_routing_system.mapper;

import com.thy.technology.flight_routing_system.dto.request.LocationRequest;
import com.thy.technology.flight_routing_system.dto.response.LocationResponse;
import com.thy.technology.flight_routing_system.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    // Veritabanından geleni (Entity), Dışarıya (Response) Çevir
    LocationResponse toResponse(Location location);

    // Dışarıdan geleni (Request), Veritabanına (Entity) Çevir
    Location toEntity(LocationRequest request);

    // Güncelleme işlemi için Request'ten gelen verileri mevcut Entity üzerine yaz
    void updateEntityFromRequest(LocationRequest request, @MappingTarget Location entity);
}
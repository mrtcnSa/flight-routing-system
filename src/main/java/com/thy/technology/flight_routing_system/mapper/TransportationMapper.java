package com.thy.technology.flight_routing_system.mapper;

import com.thy.technology.flight_routing_system.dto.request.TransportationRequest;
import com.thy.technology.flight_routing_system.dto.response.TransportationResponse;
import com.thy.technology.flight_routing_system.model.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransportationMapper {

    // 1. Entity -> Response
    @Mapping(source = "originLocation.id", target = "originLocationId")
    @Mapping(source = "destinationLocation.id", target = "destinationLocationId")
    TransportationResponse toResponse(Transportation transportation);

    // 2. Request -> Entity
    @Mapping(target = "originLocation", ignore = true) // Service'te bizzat bulacağız
    @Mapping(target = "destinationLocation", ignore = true)
    @Mapping(target = "id", ignore = true)
    Transportation toEntity(TransportationRequest request);

    // 3. Update Mevcut Entity
    @Mapping(target = "originLocation", ignore = true)
    @Mapping(target = "destinationLocation", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(TransportationRequest request, @MappingTarget Transportation entity);
}
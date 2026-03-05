package com.thy.technology.flight_routing_system.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "transportations")


public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Location originLocation;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Location destinationLocation;

    @Enumerated(EnumType.STRING)
    private TransportationType type; // Artık dışarıdaki public enum'ı kullanıyor


    @ElementCollection(fetch = FetchType.EAGER) // LAZY yerine EAGER yapıyoruz!
    @CollectionTable(name = "transportation_days", joinColumns = @JoinColumn(name = "transportation_id"))
    @Column(name = "day_of_week")
    private List<Integer> operatingDays = new ArrayList<>();
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Location getOriginLocation() {
		return originLocation;
	}

	public void setOriginLocation(Location originLocation) {
		this.originLocation = originLocation;
	}

	public Location getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(Location destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public TransportationType getType() {
		return type;
	}

	public void setType(TransportationType type) {
		this.type = type;
	}

	public List<Integer> getOperatingDays() {
		return operatingDays;
	}

	public void setOperatingDays(List<Integer> operatingDays) {
		this.operatingDays = operatingDays;
	}
    
    
}
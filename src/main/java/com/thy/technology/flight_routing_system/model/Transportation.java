package com.thy.technology.flight_routing_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transportations")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private TransportationType type;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "transportation_days", joinColumns = @JoinColumn(name = "transportation_id"))
    @Column(name = "day_of_week")
    private List<Integer> operatingDays = new ArrayList<>();
}


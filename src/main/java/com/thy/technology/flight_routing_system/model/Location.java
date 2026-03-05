package com.thy.technology.flight_routing_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(name = "location_code", nullable = false, unique = true)
    private String locationCode;



    // Bu lokasyonun kalkıs noktası olduğu ulaşımlar
    @OneToMany(mappedBy = "originLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Transportation> departingTransportations = new ArrayList<>();

    // Bu lokasyonun varıs noktası olduğu ulaşımlar
    @OneToMany(mappedBy = "destinationLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Transportation> arrivingTransportations = new ArrayList<>();
}
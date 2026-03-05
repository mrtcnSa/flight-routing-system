package com.thy.technology.flight_routing_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // Cache active
public class FlightRoutingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightRoutingSystemApplication.class, args);
	}

}

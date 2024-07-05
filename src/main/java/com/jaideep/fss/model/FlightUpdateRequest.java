package com.jaideep.fss.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public record FlightUpdateRequest(
        Long id,
        String flightNumber,
        String origin,
        String destination,
        LocalDate departureDate,
        LocalDate arrivalDate,
        double amount,
        int totalSeats,
        int availableSeats) {

}

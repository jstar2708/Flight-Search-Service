package com.jaideep.fss.entity;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "flights")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    private Long id;
    String flightNumber;
    String origin;
    String destination;
    LocalDate departureDate;
    LocalDate arrivalDate;
    double amount;
    int totalSeats;
    int availableSeats;

}

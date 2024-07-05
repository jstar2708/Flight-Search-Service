package com.jaideep.fss.repository;

import com.jaideep.fss.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class FlightSearchRepositoryTest {
    @Autowired
    private FlightSearchRepository flightSearchRepository;

    @BeforeEach
    void setUp() {
        Flight flightOne = new Flight(0L, "BA234", "Vadodara", "Indore", LocalDate.of(2024, 1, 12), LocalDate.of(2024, 1, 13), 1230.45d, 500, 124);
        Flight flightTwo = new Flight(1L, "WA321", "Delhi", "Mumbai", LocalDate.of(2024, 1, 18), LocalDate.of(2024, 1, 19), 1210.45d, 560, 325);
        flightSearchRepository.save(flightOne);
        flightSearchRepository.save(flightTwo);
    }

    @Test
    void findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual_Success_Test() {
        String origin = "Vadodara";
        String destination = "Indore";
        LocalDate date = LocalDate.of(2024, 1, 12);
        int passengers = 2;

        List<Flight> flights = flightSearchRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(origin, destination, date, passengers);
        assertNotNull(flights);
        assertThat(flights).hasSize(1);
        assertThat(flights.get(0).getOrigin()).isEqualTo(origin);
        assertThat(flights.get(0).getDepartureDate()).isBeforeOrEqualTo(date);
        assertThat(flights.get(0).getAvailableSeats()).isGreaterThan(passengers);
    }

    @Test
    void findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual_Origin_Failure_Test() {
        List<Flight> flights = flightSearchRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual("Mumbai", "Indore", LocalDate.of(2024, 1, 12), 2);

        assertNotNull(flights);
        assertThat(flights).isEmpty();
    }

    @Test
    void findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual_Destination_Failure_Test() {
        Flight flightOne = new Flight(0L, "BA234", "Vadodara", "Indore", LocalDate.of(2024, 1, 12), LocalDate.of(2024, 1, 13), 1230.45d, 500, 124);
        Flight flightTwo = new Flight(1L, "WA321", "Delhi", "Mumbai", LocalDate.of(2024, 1, 18), LocalDate.of(2024, 1, 19), 1210.45d, 560, 325);
        flightSearchRepository.save(flightOne);
        flightSearchRepository.save(flightTwo);
        String origin = "Vadodara";
        String destination = "Bhopal";
        LocalDate date = LocalDate.of(2024, 1, 12);
        int passengers = 2;

        List<Flight> flights = flightSearchRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(origin, destination, date, passengers);
        assertNotNull(flights);
        assertThat(flights).isEmpty();
    }

    @Test
    void findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual_DepartureDate_Failure_Test() {
        String origin = "Vadodara";
        String destination = "Indore";
        LocalDate date = LocalDate.of(2024, 2, 12);
        int passengers = 2;

        List<Flight> flights = flightSearchRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(origin, destination, date, passengers);
        assertNotNull(flights);
        assertThat(flights).isEmpty();
    }

    @Test
    void findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual_AvailableSeats_Failure_Test() {
        String origin = "Vadodara";
        String destination = "Indore";
        LocalDate date = LocalDate.of(2024, 1, 12);
        int passengers = 400;

        List<Flight> flights = flightSearchRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(origin, destination, date, passengers);
        assertNotNull(flights);
        assertThat(flights).isEmpty();
    }

}

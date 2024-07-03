package com.jaideep.fss.service;

import com.jaideep.fss.entity.Flight;
import com.jaideep.fss.model.FlightSearchRequest;
import com.jaideep.fss.model.FlightSearchResponse;
import com.jaideep.fss.repository.FlightSearchRepository;
import com.jaideep.fss.service.serviceimpl.FlightSearchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightSearchServiceTest {
    @InjectMocks
    private FlightSearchServiceImpl flightSearchService;
    @Mock
    private FlightSearchRepository flightSearchRepository;
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight(1L, "WA321", "Delhi", "Mumbai", LocalDate.of(2024, 1, 18), LocalDate.of(2024, 1, 19), 1210.45d, 560, 325);

    }

    @Test
    void searchFlight_HasFlightsAvailable_Test() {
        String origin = "Delhi";
        String destination = "Mumbai";
        LocalDate travelDate = LocalDate.of(2024, 1, 18);
        int passengers = 8;
        List<Flight> availableFlights = new ArrayList<>();
        availableFlights.add(flight);
        FlightSearchRequest flightSearchRequest = new FlightSearchRequest(origin, destination, travelDate, passengers);
        when(flightSearchRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(origin, destination, travelDate, passengers)).thenReturn(availableFlights);

        List<FlightSearchResponse> flightSearchResponses = flightSearchService.searchFlights(flightSearchRequest);

        assertNotNull(flightSearchResponses);
        assertThat(flightSearchResponses).hasSize(1);
        assertEquals(flightSearchResponses.get(0).getDestination(), destination);
    }

    @Test
    void searchFlight_NoAvailableFlights_Test() {
        String origin = "Kolkata";
        String destination = "Mumbai";
        LocalDate travelDate = LocalDate.of(2024, 1, 18);
        int passengers = 8;
        List<Flight> availableFlights = new ArrayList<>();
        FlightSearchRequest flightSearchRequest = new FlightSearchRequest(origin, destination, travelDate, passengers);
        when(flightSearchRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(origin, destination, travelDate, passengers)).thenReturn(availableFlights);

        List<FlightSearchResponse> flightSearchResponses = flightSearchService.searchFlights(flightSearchRequest);

        assertNotNull(flightSearchResponses);
        assertThat(flightSearchResponses).isEmpty();
    }
}

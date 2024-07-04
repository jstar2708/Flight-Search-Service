package com.jaideep.fss.integration;

import com.jaideep.fss.entity.Flight;
import com.jaideep.fss.model.FlightSearchRequest;
import com.jaideep.fss.model.FlightSearchResponse;
import com.jaideep.fss.repository.FlightSearchRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FlightSearchIntegrationTest {
    private static RestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";
    @Autowired
    private FlightSearchRepository flightSearchRepository;

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setUpBefore() {
        baseUrl = baseUrl + ":" + port + "/v1/api/search";
        Flight flightOne = new Flight(0L, "BA234", "Vadodara", "Indore", LocalDate.of(2024, 1, 12), LocalDate.of(2024, 1, 13), 1230.45d, 500, 124);
        Flight flightTwo = new Flight(1L, "WA321", "Delhi", "Mumbai", LocalDate.of(2024, 1, 18), LocalDate.of(2024, 1, 19), 1210.45d, 560, 325);
        flightSearchRepository.save(flightOne);
        flightSearchRepository.save(flightTwo);
    }

    @Test
    void shouldGetAvailableFlights() {
        FlightSearchRequest flightSearchRequest = new FlightSearchRequest("Delhi", "Mumbai", LocalDate.of(2024, 1, 18), 8);
        List<FlightSearchResponse> flightSearchResponses = restTemplate.postForObject(baseUrl + "/flights", flightSearchRequest, List.class);
        assertNotNull(flightSearchResponses);
        assertEquals(1, flightSearchResponses.size());
    }
}

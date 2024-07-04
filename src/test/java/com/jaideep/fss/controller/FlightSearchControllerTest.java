package com.jaideep.fss.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaideep.fss.model.FlightSearchRequest;
import com.jaideep.fss.model.FlightSearchResponse;
import com.jaideep.fss.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FlightSearchControllerTest {
    @MockBean
    private FlightSearchService flightSearchService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private FlightSearchResponse flightSearchResponse;

    @BeforeEach
    void setUp() {
        flightSearchResponse = new FlightSearchResponse(1L, "WA321", "Delhi", "Mumbai", LocalDate.of(2024, 1, 18), LocalDate.of(2024, 1, 19), 1210.45d, 560, 325);
    }

    @Test
    void searchFlight_HasFlightsAvailable_Test() throws Exception {
        List<FlightSearchResponse> flightSearchResponses = new ArrayList<>();
        flightSearchResponses.add(flightSearchResponse);
        FlightSearchRequest flightSearchRequest = new FlightSearchRequest("Delhi", "Mumbai", LocalDate.of(2024, 1, 18), 8);

        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(flightSearchResponses);

        this.mockMvc.perform(post("/v1/api/search/flights").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(flightSearchRequest))).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(flightSearchResponses.size())));
    }

    @Test
    void searchFlight_NoFlightsAvailable_Test() throws Exception {
        FlightSearchRequest flightSearchRequest = new FlightSearchRequest("Kolkata", "Mumbai", LocalDate.of(2024, 1, 18), 8);

        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(new ArrayList<>());

        this.mockMvc.perform(post("/v1/api/search/flights").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(flightSearchRequest))).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(0)));
    }

}

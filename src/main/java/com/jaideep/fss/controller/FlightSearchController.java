package com.jaideep.fss.controller;

import com.jaideep.fss.model.FlightSearchRequest;
import com.jaideep.fss.model.FlightSearchResponse;
import com.jaideep.fss.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/search")
public class FlightSearchController {
    private final FlightSearchService flightSearchService;

    @PostMapping("/flights")
    public ResponseEntity<List<FlightSearchResponse>> searchFlight(@RequestBody FlightSearchRequest flightSearchRequest) {
        return new ResponseEntity<>(flightSearchService.searchFlights(flightSearchRequest), HttpStatus.OK);
    }

}

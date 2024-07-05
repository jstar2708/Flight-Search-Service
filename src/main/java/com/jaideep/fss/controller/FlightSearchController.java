package com.jaideep.fss.controller;

import com.jaideep.fss.model.FlightSearchRequest;
import com.jaideep.fss.model.FlightSearchResponse;
import com.jaideep.fss.model.FlightUpdateRequest;
import com.jaideep.fss.service.FlightSearchService;
import com.jaideep.fss.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/search")
@Slf4j
public class FlightSearchController {
    private final FlightSearchService flightSearchService;
    private final FlightService flightService;

    @PostMapping("/flights")
    public ResponseEntity<List<FlightSearchResponse>> searchFlight(@RequestBody FlightSearchRequest flightSearchRequest) {
        return new ResponseEntity<>(flightSearchService.searchFlights(flightSearchRequest), HttpStatus.OK);
    }

    @PostMapping("/addFlight")
    @ResponseStatus(HttpStatus.CREATED)
    public String addFlight(@RequestBody FlightUpdateRequest flightUpdateRequest) {
        log.info("Received Flight addition request : " + flightUpdateRequest);
        flightService.addFlight(flightUpdateRequest);
        return "Success";
    }

    @PutMapping("/updateFlight")
    @ResponseStatus(HttpStatus.OK)
    public String updateFlight(@RequestBody FlightUpdateRequest flightUpdateRequest) {
        log.info("Received flight update request : " + flightUpdateRequest);
        flightService.updateFlightDetails(flightUpdateRequest);
        return "Updated";
    }

}

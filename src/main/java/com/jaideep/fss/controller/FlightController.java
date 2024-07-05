package com.jaideep.fss.controller;

import com.jaideep.fss.model.FlightUpdateRequest;
import com.jaideep.fss.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1/api/flight")
@RequiredArgsConstructor
@Slf4j
public class FlightController {
    private final FlightService flightService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addFlight(@RequestBody FlightUpdateRequest flightUpdateRequest) {
        log.info("Received Flight addition request : " + flightUpdateRequest);
        flightService.addFlight(flightUpdateRequest);
        return "Success";
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public String updateFlight(@RequestBody FlightUpdateRequest flightUpdateRequest) {
        log.info("Received flight update request : " + flightUpdateRequest);
        flightService.updateFlightDetails(flightUpdateRequest);
        return "Updated";
    }
}

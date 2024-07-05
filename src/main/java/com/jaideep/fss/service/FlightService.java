package com.jaideep.fss.service;

import com.jaideep.fss.model.FlightUpdateRequest;

public interface FlightService {
    void updateFlightDetails(FlightUpdateRequest flightUpdateRequest);
    void addFlight(FlightUpdateRequest flightUpdateRequest);
}

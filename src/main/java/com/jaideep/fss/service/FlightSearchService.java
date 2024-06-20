package com.jaideep.fss.service;

import com.jaideep.fss.model.FlightSearchRequest;
import com.jaideep.fss.model.FlightSearchResponse;

import java.util.List;

public interface FlightSearchService {
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest flightSearchRequest);
}

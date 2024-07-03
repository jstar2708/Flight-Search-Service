package com.jaideep.fss.service.serviceimpl;

import com.jaideep.fss.entity.Flight;
import com.jaideep.fss.model.FlightSearchRequest;
import com.jaideep.fss.model.FlightSearchResponse;
import com.jaideep.fss.repository.FlightSearchRepository;
import com.jaideep.fss.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements FlightSearchService {
    private final FlightSearchRepository flightSearchRepository;

    @Override
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest flightSearchRequest) {
        log.info("Searching for flights with request : " + flightSearchRequest);
        List<Flight> flights = flightSearchRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(flightSearchRequest.origin(), flightSearchRequest.destination(), flightSearchRequest.travelDate(), flightSearchRequest.passengers());
        log.info("Number of available flights : " + flights.size());
        return flights.parallelStream().map(this::mapToFlightSearchResponse).toList();
    }

    private FlightSearchResponse mapToFlightSearchResponse(Flight flight) {
        FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
        BeanUtils.copyProperties(flight, flightSearchResponse);
        return flightSearchResponse;
    }
}

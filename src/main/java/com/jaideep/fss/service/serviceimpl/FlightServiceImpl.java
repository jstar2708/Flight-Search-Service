package com.jaideep.fss.service.serviceimpl;

import com.jaideep.fss.entity.Flight;
import com.jaideep.fss.model.FlightUpdateRequest;
import com.jaideep.fss.repository.FlightSearchRepository;
import com.jaideep.fss.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightServiceImpl implements FlightService {
    private final FlightSearchRepository flightSearchRepository;

    @Override
    public void updateFlightDetails(FlightUpdateRequest flightUpdateRequest) {
        log.info("Searching for the required flight with number : " + flightUpdateRequest.flightNumber());
        Flight flight = flightSearchRepository.findByFlightNumberAndDepartureDate(flightUpdateRequest.flightNumber(), flightUpdateRequest.departureDate());
        flight.setAvailableSeats(flightUpdateRequest.availableSeats());
        flight.setAmount(flightUpdateRequest.amount());
        flight.setDestination(flightUpdateRequest.destination());
        flight.setOrigin(flightUpdateRequest.origin());
        flight.setArrivalDate(flightUpdateRequest.arrivalDate());
        flight.setTotalSeats(flightUpdateRequest.totalSeats());
        flightSearchRepository.save(flight);
        log.info("Updated flight with number : " + flight.getFlightNumber());
    }

    @Override
    public void addFlight(FlightUpdateRequest flightUpdateRequest) {
        Flight flight = new Flight();
        flight.setAvailableSeats(flightUpdateRequest.availableSeats());
        flight.setAmount(flightUpdateRequest.amount());
        flight.setDestination(flightUpdateRequest.destination());
        flight.setOrigin(flightUpdateRequest.origin());
        flight.setArrivalDate(flightUpdateRequest.arrivalDate());
        flight.setTotalSeats(flightUpdateRequest.totalSeats());
        flightSearchRepository.save(flight);
        log.info("Added flight with number : " + flight.getFlightNumber());
    }
}

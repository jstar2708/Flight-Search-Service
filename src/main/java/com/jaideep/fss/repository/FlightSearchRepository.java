package com.jaideep.fss.repository;

import com.jaideep.fss.entity.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightSearchRepository extends MongoRepository<Flight, Long> {
    List<Flight> findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(
            String origin,
            String destination,
            LocalDate date,
            int passengers
    );
}

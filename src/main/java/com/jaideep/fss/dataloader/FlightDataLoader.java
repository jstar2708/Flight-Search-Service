package com.jaideep.fss.dataloader;

import com.jaideep.fss.entity.Flight;
import com.jaideep.fss.repository.FlightSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class FlightDataLoader implements CommandLineRunner {
    private final FlightSearchRepository flightSearchRepository;
    @Override
    public void run(String... args) throws Exception {
        //Remove all the flights
        flightSearchRepository.deleteAll();

        //create sample flights
        Flight flight1 = new Flight(
                1L,
                "EK204",
                "DXB",
                "TMK",
                LocalDate.parse("2024-06-22"),
                LocalDate.parse("2024-06-22"),
                500d,
                250,
                234
        );
        Flight flight2 = new Flight(
                2L,
                "EK205",
                "DXB",
                "SME",
                LocalDate.parse("2024-06-24"),
                LocalDate.parse("2024-06-24"),
                600d,
                300,
                154
        );
        Flight flight3 = new Flight(
                3L,
                "EK206",
                "IND",
                "USA",
                LocalDate.parse("2024-06-27"),
                LocalDate.parse("2024-06-27"),
                1900d,
                200,
                34
        );

        //Save to DB
        flightSearchRepository.saveAll(Arrays.asList(flight1, flight2, flight3));
    }
}

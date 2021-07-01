package com.karpenkov.flightmonitoringuser.service;
import  com.github.A-dev-cyber.flight-monitoringClient.src.main.java.flightPrices.FlightPricesDto;
// Service, for getting details based on {@link Subscription} object

import com.karpenkov.flightmonitoringuser.repository.Subscription;

public interface FlightPriceService {
//  Finds minPrice based on {@link Subscription}.
// @param flightPricesDto provided {@link FlightPricesDto} object.

    Integer findMinPrice(FlightPricesDto flightPricesDto);
    //Finds all the flight data related to {@link Subscription} object.
    //@param subscription provided {@link Subscription} object
    // @return {@link FlightPricesDto} with all the data related to flight specific in {@link Subscription}.

    FlightPricesDto findFlightPrice(Subscription subscription);
}

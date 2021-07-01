package com.karpenkov.flightmonitoringuser.service.impl;

import com.karpenkov.flightmonitoringuser.repository.SubscriptionRepository;
import com.karpenkov.flightmonitoringuser.service.EmailNotifierService;
import com.karpenkov.flightmonitoringuser.service.FlightPriceService;
import com.karpenkov.flightmonitoringuser.service.RecountMinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RecountMinPriceServiceImpl implements RecountMinPriceService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private FlightPriceService flightPriceService;
    @Autowired
    private EmailNotifierService emailNotifierService;


    @Override
    public void recount() {
    subscriptionRepository.findAll().forEach(subscription -> {
        if (subscription.getOutboundPartialDate().isAfter(LocalDate.now().minusDays(1))){
            FlightPricesDto flightPricesDto = flightPriceService.findFlightPrice(subscription);
            Integer newNumPrice = flightPriceService.findMinPrice(flightPricesDto);
            if  (subscription.getMinPrice()> newNumPrice){
                emailNotifierService.notifySubscriber(subscription, subscription.getMinPrice(), newNumPrice);
                subscription.setMinPrice(newNumPrice);
                subscriptionRepository.save(subscription);
            }
        } else {
            subscriptionRepository.delete(subscription);
        }
        });
    }
}

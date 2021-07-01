package com.karpenkov.flightmonitoringuser.service.impl;
import com.github.A-dev-cyber.flight-monitoringClient.src.main.java.FlightPricesDto;
import com.github.A-dev-cyber.flight-monitoringClient.src.main.java.FlightPrices;
import com.karpenkov.flightmonitoringuser.repository.Subscription;
import com.karpenkov.flightmonitoringuser.service.FlightPriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karpenkov.flightmonitoringuser.repository.Subscription;
import com.karpenkov.flightmonitoringuser.service.FlightPriceService;
import org.springframework.beans.factory.annotation.Autowired;

public class FlightPriceServiceImpl implements FlightPriceService {
@Autowired

private FligthPricesClient client = new FlightPriceClientImpl();

    @Override
    public Integer findMinPrice(FlightPricesDto flightPricesDto) {
        return flightPricesDto.getQuotas().get(0).getMinPrice();;
    }

    @Override
    public FlightPricesDto findFlightPrice(Subscription subscription) {
        if (subscription.getInboundPartialDate() == null){
            return client.browseQuotes(subscription.getCountry(), subscription.getCurrency(), subscription.getLocale(),
                    subscription.getOriginPlace(), subscription.getDestinationPlace(),
                    subscription.getOutboundPartialDate().toString());
        }
        else {
            return client
                    .browseQuotes(subscription.getCountry(), subscription.getCurrency(), subscription.getLocale(),
                            subscription.getOriginPlace(), subscription.getDestinationPlace(),
                            subscription.getOutboundPartialDate().toString(), subscription.getInboundPartialDate().toString());
        }
    }
}

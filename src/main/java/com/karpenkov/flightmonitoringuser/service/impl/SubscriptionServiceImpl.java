package com.karpenkov.flightmonitoringuser.service.impl;

import com.example.flightmonitoring.flightPrices.FlightPricesDto;
import com.karpenkov.flightmonitoringuser.repository.Subscription;
import com.karpenkov.flightmonitoringuser.repository.SubscriptionRepository;
import com.karpenkov.flightmonitoringuser.rest.dto.SubscriptionCreateDto;
import com.karpenkov.flightmonitoringuser.rest.dto.SubscriptionDto;
import com.karpenkov.flightmonitoringuser.rest.dto.SubscriptionUpdateDto;
import com.karpenkov.flightmonitoringuser.service.EmailNotifierService;
import com.karpenkov.flightmonitoringuser.service.FlightPriceService;
import com.karpenkov.flightmonitoringuser.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private FlightPriceService flightPriceService;

    @Autowired
    private EmailNotifierService emailNotifierService;


    @Override
    public SubscriptionDto create(SubscriptionCreateDto dto) {
        Subscription subscription = toEntity(dto);
        Optional<Subscription> one = subscriptionRepository.findOne(Example.of(subscription));
        if (one.isPresent()) {
            log.info("The same subscription has been found for Subscription={}", subscription);
            Subscription fromDatabase = one.get();
            FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
            subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesResponse));
            return toDto(fromDatabase, flightPricesResponse);
        } else {
            FligthPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
            subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesResponse));
            Subscription saved = subscriptionRepository.save(subscription);
            return toDto(saved, flightPricesResponse);
        }
    }

    @Override
    public List<SubscriptionDto> findByEmail(String email) {
        return subscriptionRepository.findByEmail(email).stream()
                .map(subscription -> {
                    FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
                    if(subscription.getMinPrice() != flightPriceService.findMinPrice(FlightpricesResponse)){
                        subscription.setMinPrice(flightPriceService.findMinPrice(FlightPricesResponse));
                        subscriptionRepository.save(subscription);
                    }
                    return toDto(subscription, FlightPricesResponse);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long subscriptionID) {
    subscriptionRepository.deleteById(subscriptionID);
    }

    @Override
    public SubscriptionDto update(Long subscriptionId, SubscriptionUpdateDto dto) {
        Subscription subscription = subscriptionRepository.getOne(subscriptionId);
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setLocale(dto.getLocale());
        subscription.setCurrency(dto.getCurrency());
        subscription.setCountry(dto.getCountry());
        subscription.setEmail(dto.getEmail());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());

        FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
        subscription.setMinPrice(flightPriceService.findMinPrice(flightPriceResponse));
        return toDto(subscriptionRepository.save(subscription), flightPriceResponse);
    }

    private Subscription toEntity(SubscriptionCreateDto dto){
        Subscription subscription = new Subscription();
        subscription.setCountry(dto.getCountry());
        subscription.setCurrency(dto.getCurrency());
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setLocale(dto.getLocale());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setEmail(dto.getEmail());

        return subscription;
    }

    private SubscriptionDto toDto (Subscription entity, FlightPricesDto response) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setEmail(entity.getEmail());
        dto.setCountry(entity.getCountry());
        dto.setCurrency(entity.getCurrency());
        dto.setLocale(entity.getLocale());
        dto.setOriginPlace(entity.getOriginPlace());
        dto.setDestinationPlace(entity.getDestinationPlace());
        dto.setOutboundPartialDate(entity.getOutboundPartialDate());
        dto.setInboundPartialDate(entity.getInboundPartialDate());
        dto.setMinPrice(entity.getMinPrice());
        dto.setId(entity.getId());
        dto.setFlightPricesDto(response);
        return dto;
    }
}

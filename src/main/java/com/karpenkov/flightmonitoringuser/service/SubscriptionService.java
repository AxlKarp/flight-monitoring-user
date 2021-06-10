package com.karpenkov.flightmonitoringuser.service;


//manipulates subscriptions


import com.karpenkov.flightmonitoringuser.rest.dto.SubscriptionCreateDto;
import com.karpenkov.flightmonitoringuser.rest.dto.SubscriptionDto;
import com.karpenkov.flightmonitoringuser.rest.dto.SubscriptionUpdateDto;

import java.util.List;

public interface SubscriptionService {
    //Add new subscription.
    //@param dto the dto of the subscription

    SubscriptionDto create(SubscriptionCreateDto dto);


//    Get all subscription based on email.
//    @param email provided email;
//    @return the collection of the {@link SubscriptionDto} objects.

    List<SubscriptionDto> findByEmail (String email);

// *Remove subscription based on it ID
//  @param subscriptionId the ID of the {@link Subscription}

    void delete(Long SubscriptionID);

//    Update subscription based on ID
//    @param subscriptionId the ID of the subscription to be updated.
//    @param dto the data to be updated.
//    @return updated {@link SubscriptionDto}

    SubscriptionDto update(Long subscriptionId, SubscriptionUpdateDto dto);

}

package com.karpenkov.flightmonitoringuser.rest.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

//Data transfer object to create a @link Subscription object
@Data
public class SubscriptionCreateDto {
    @NotNull
    @Email
    @ApiModelProperty(value = "Subscriber's email", example = "example.flights.monitoring@gmail.com")
    private String email;


}

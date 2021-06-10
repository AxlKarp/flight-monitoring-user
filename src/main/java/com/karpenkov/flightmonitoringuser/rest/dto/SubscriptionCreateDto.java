package com.karpenkov.flightmonitoringuser.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;

//Data transfer object to create a @link Subscription object
@Data
public class SubscriptionCreateDto {
    @NotNull
    @Email
    @ApiModelProperty(value = "Subscriber's email", example = "example.flights.monitoring@gmail.com")
    private String email;

    @NotNull
    @ApiModelProperty(value = "Country code", example = "UA")
    private String country;

    @NotNull
    @ApiModelProperty(value = "Locale", example = "ru-RU")
    private String locale;

    @NotNull
    @ApiModelProperty(value = "Code of origin place", example = "HRK-sky")
    private String originPlace;

    @NotNull
    @ApiModelProperty(value = "Code of the destination place",example ="KBP-sky")
    private String destinationPlace;


    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Date front", example = "2019-12-18")
    private LocalDate outboundPartialDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Date back", example = "2019-12-25")
    private LocalDate inboundPartialDate;
}

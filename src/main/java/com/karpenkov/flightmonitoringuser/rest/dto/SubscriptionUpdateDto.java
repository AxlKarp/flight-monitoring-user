package com.karpenkov.flightmonitoringuser.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class SubscriptionUpdateDto {
    @NotNull
    @Email
    @ApiModelProperty(value = "Subscriber's email",example = "example.flights.monitoring@gmail.com" )
    private String Email;

    @NotNull
    @ApiModelProperty(value="Country code", example = "UA")
    private String country;

    @NotNull
    @ApiModelProperty(value = "Currency",example = "UAH")
    private String currency;

    @NotNull
    @ApiModelProperty(value = "Locale",example = "ru-RU")
    private String locale;

    @NotNull
    @ApiModelProperty(value = "Code of origin place",example = "HRK-scy")
    private String originPlace;

    @NotNull
    @ApiModelProperty(value = "Code of destination place", example = "KBP-scy")
    private String destinationPlace;

    @NotNull
    @JsonFormat(pattern ="yyyy-MM-dd")
    @ApiModelProperty(value = "Date front", example = "2019-12-18")
    private LocalDate outBoundPartialDate;

    @JsonFormat(pattern = "yyyy-DD-mm")
    @ApiModelProperty(value = "Date back", example = "2019-12-18")
    private LocalDate inBoundPartialDate;
}

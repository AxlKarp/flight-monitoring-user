package com.karpenkov.flightmonitoringuser.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
@Data
public class SubscriptionDto {
private Long id;

@NotNull
@ApiModelProperty(value = "Subscriber's email", example = "test@test.com")
private String email;

@NotNull
@ApiModelProperty(value = "Country Code", example = "UA")
private String country;

@NotNull
@ApiModelProperty(value = "Currency code", example = "UAH")
private String currency;

@NotNull
@ApiModelProperty(value = "Locale", example = "ru-RU")
private String locale;

@NotNull
@ApiModelProperty(value = "Code of the origin place", example = "HRK-sky")
private String originPlace;

@NotNull
@ApiModelProperty(value = "Code of the destination place", example = "KBP-sky")
private String destinationPlace;

@NotNull
@JsonFormat(pattern = "yyyy-MM-dd")
@ApiModelProperty(value = "Date front", example = "2019-12-18")
private LocalDate outBoundPartialDate;

@JsonFormat(pattern = "yyyy-MM-dd")
@ApiModelProperty(value = "Date back", example = "2019-12-18")
private  LocalDate inBoundPartialDate;

@ApiModelProperty(value = "Min price based on all this data", example = "100")
private Integer minPrice;

@ApiModelProperty(value = "Response which contains all the need info about min price flight")
private FlightPricesDto flightPricesDto;
}

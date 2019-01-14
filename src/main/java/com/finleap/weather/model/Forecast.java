package com.finleap.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    private BigInteger dt;
    private String dt_txt;

    private ForecastMain main;


}

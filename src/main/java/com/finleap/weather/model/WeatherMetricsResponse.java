package com.finleap.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherMetricsResponse {

    private BigDecimal averageDailyTemp;
    private BigDecimal averageNightlyTemp;
    private BigDecimal averagePressure;

}

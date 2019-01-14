package com.finleap.weather.service;

import com.finleap.weather.apicaller.ForecastRequestClient;
import com.finleap.weather.exception.DataNotFoundException;
import com.finleap.weather.model.Forecast;
import com.finleap.weather.model.ForecastResponse;
import com.finleap.weather.model.WeatherMetricsResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class WeatherMetricServiceImpl implements WeatherMetricService {

    private ForecastRequestClient forecastRequestClient;
    private DateCheckerService dateCheckerService;

    private static final String appId = "fbdaa375ca8c18780b2e5e12d9f6de2a";

    public WeatherMetricsResponse getWeatherMetric(String city) throws DataNotFoundException {

        ForecastResponse forecastResponse = forecastRequestClient.getForecast(city, appId);

        if(CollectionUtils.isEmpty(forecastResponse.getList())) {
            throw new DataNotFoundException("Data not found for : " + forecastResponse.getMessage());
        }

        Map<Boolean, List<Forecast>> filteredData = forecastResponse.getList().stream()
                .map(Objects::requireNonNull)
                .filter(data -> Objects.nonNull(data.getDt()))
                .filter(data -> dateCheckerService.isDateInRange(data.getDt().longValue()))
                .collect(Collectors.partitioningBy(data -> dateCheckerService.isTimeInRange(data.getDt().longValue())));

        OptionalDouble avgDailyTemp = filteredData.get(true).parallelStream().filter(data -> Objects.nonNull(data.getMain().getTemp()))
                .mapToDouble(data -> data.getMain().getTemp().doubleValue())
                .average();

        BigDecimal avgDailyTempInCelsius = avgDailyTemp.isPresent() ? new BigDecimal(avgDailyTemp.getAsDouble()).setScale(2, RoundingMode.HALF_DOWN)
                .subtract(BigDecimal.valueOf(273.15)) :
                BigDecimal.ZERO;

        OptionalDouble avgNightlyTemp = filteredData.get(false).parallelStream().filter(data -> Objects.nonNull(data.getMain().getTemp()))
                .mapToDouble(data -> data.getMain().getTemp().doubleValue())
                .average();

        BigDecimal avgNightlyTempInCelsius =  avgNightlyTemp.isPresent() ? new BigDecimal(avgNightlyTemp.getAsDouble()).setScale(2, RoundingMode.HALF_DOWN)
                .subtract(BigDecimal.valueOf(273.15)) :
                BigDecimal.ZERO;


        OptionalDouble avgPressure = Stream.concat(filteredData.get(true).stream(),filteredData.get(false).stream()).filter(data -> Objects.nonNull(data.getMain().getPressure()))
                .mapToDouble(d -> d.getMain().getPressure().doubleValue()).average();

        BigDecimal avgPressureCalc = avgPressure.isPresent() ? new BigDecimal(avgPressure.getAsDouble()).setScale(2, RoundingMode.HALF_DOWN) : BigDecimal.ZERO;


        return WeatherMetricsResponse.builder()
                .averageDailyTemp(avgDailyTempInCelsius)
                .averageNightlyTemp(avgNightlyTempInCelsius)
                .averagePressure(avgPressureCalc).build();
    }
}

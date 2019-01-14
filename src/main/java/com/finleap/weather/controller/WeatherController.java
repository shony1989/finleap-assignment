package com.finleap.weather.controller;

import com.finleap.weather.exception.CityNotFoundException;
import com.finleap.weather.exception.DataNotFoundException;
import com.finleap.weather.model.WeatherMetricsResponse;
import com.finleap.weather.service.WeatherMetricService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@AllArgsConstructor
@RestController
@Slf4j
public class WeatherController {

    private WeatherMetricService weatherMetricService;

    @Cacheable(value = "get-weather", key = "#city")
    @GetMapping("/data")
    public ResponseEntity<WeatherMetricsResponse> getWeatherMetric(@RequestParam(value = "city") String city) throws DataNotFoundException, CityNotFoundException {

        if(Objects.isNull(city) || city.isEmpty()) {
          throw new CityNotFoundException("wrong entry");
        }
        log.info("get metric with city {}", city);
        return new ResponseEntity<>(weatherMetricService.getWeatherMetric(city.toLowerCase()), HttpStatus.OK);
    }

}

package com.finleap.weather.service;

import com.finleap.weather.exception.DataNotFoundException;
import com.finleap.weather.model.WeatherMetricsResponse;

public interface WeatherMetricService {

    /**
     *Get the Metric Report in celsius
     * @param city
     * @return
     */
    WeatherMetricsResponse getWeatherMetric(String city) throws DataNotFoundException;
}

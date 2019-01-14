package com.finleap.weather.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.finleap.weather.apicaller.ForecastRequestClient;
import com.finleap.weather.model.ForecastResponse;
import com.finleap.weather.model.WeatherMetricsResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Scanner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherMetricServiceTest {

    @Autowired
    private WeatherMetricService service;

    @MockBean
    private ForecastRequestClient forecastRequestClient;

    @Test
    public void testWeatherMetric() throws Exception {

        String forecastJson = new Scanner(new File("forecast-response.json")).useDelimiter("\\Z").next();

        Gson gson = new Gson();

        ForecastResponse response = gson.fromJson(forecastJson, ForecastResponse.class);

        when(forecastRequestClient.getForecast(anyString(), anyString())).thenReturn(response);

        WeatherMetricsResponse weatherMetricsResponse = service.getWeatherMetric("xyz");

        log.info("response is [{}]", weatherMetricsResponse);

        System.out.println(weatherMetricsResponse.toString());

        assertThat(weatherMetricsResponse.getAverageDailyTemp()).as("Success - expected avg daily temp").isNotNull();
        assertThat(weatherMetricsResponse.getAverageNightlyTemp()).as("Success - expected avg nightly temp").isNotNull();
        assertThat(weatherMetricsResponse.getAveragePressure()).as("Success - expected pressure").isNotNull();

    }
}

package com.finleap.weather.controller;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.finleap.weather.apicaller.ForecastRequestClient;
import com.finleap.weather.model.ForecastResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeatherControllerIntegrationTest {

    protected MockMvc mvc;

    @MockBean
    private ForecastRequestClient forecastRequestClient;

    @Autowired
    private WeatherController controller;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testWeatherMetric() throws Exception {

        String forecastJson = new Scanner(new File("forecast-response.json")).useDelimiter("\\Z").next();

        Gson gson = new Gson();

        ForecastResponse response = gson.fromJson(forecastJson, ForecastResponse.class);

        when(forecastRequestClient.getForecast(anyString(), anyString())).thenReturn(response);

        String uri = "/data?city=london";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)).andDo(print())
              .andReturn();

        int status = result.getResponse().getStatus();
        log.info("status is [{}]", status);

        assertThat(status).as("Success - expected status 200").isEqualTo(200);

    }
}

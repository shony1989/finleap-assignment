package com.finleap.weather.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.finleap.weather.model.WeatherMetricsResponse;
import com.finleap.weather.service.WeatherMetricService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@Slf4j
public class WeatherControllerTest {

    protected MockMvc mvc;

    @Mock
    private WeatherMetricService weatherMetricService;

    @InjectMocks
    private WeatherController controller;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testWeatherMetric() throws Exception {

        when(weatherMetricService.getWeatherMetric(anyString())).thenReturn(new WeatherMetricsResponse());

        String uri = "/data?city=london";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = result.getResponse().getStatus();
        log.info("status is [{}]", status);

        verify(weatherMetricService, times(1)).getWeatherMetric(any());
        assertThat(status).as("Success - expected status 200").isEqualTo(200);
    }


}

package com.finleap.weather.apicaller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finleap.weather.model.ForecastResponse;

import feign.Headers;

/**
 * Client for OpenWeather API
 */
@FeignClient(name = "${openweather.client.name}" , url = "${openweather.client.url}" , fallbackFactory = ForecastRequestClientFallbackFactory.class)
@Headers("Accept: application/json")
public interface ForecastRequestClient {

	@GetMapping("/data/2.5/forecast")
	ForecastResponse getForecast(@RequestParam(value = "q") String city, @RequestParam(value = "APPID") String appId);

}

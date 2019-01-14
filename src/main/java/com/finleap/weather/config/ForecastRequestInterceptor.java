package com.finleap.weather.config;

import lombok.extern.slf4j.Slf4j;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Slf4j
public class ForecastRequestInterceptor implements RequestInterceptor {

	
	@Override
	public void apply(RequestTemplate template) {
		log.info("request send",template.url());
		
	}

}

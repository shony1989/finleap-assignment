package com.finleap.weather.apicaller;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ForecastRequestClientFallbackFactory implements FallbackFactory<ForecastRequestClient> {

    @Override
    public ForecastRequestClient create(Throwable throwable) {
        return new ForecastRequestClientFallback(throwable);
    }

}

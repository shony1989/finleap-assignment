package com.finleap.weather.apicaller;

import com.finleap.weather.model.ForecastResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

/**
 * Foreback method In case In any other in Inter service commnunicationj
 */
@Slf4j
public class ForecastRequestClientFallback implements ForecastRequestClient {

    private final Throwable cause;

    public ForecastRequestClientFallback(Throwable cause) {
        this.cause = cause;
    }

    public ForecastResponse getForecast(String city, String appId) {
        if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
            // Treat the HTTP 404 status
            log.error("", cause);
            return new ForecastResponse().builder().message(cause.getMessage().substring(
                    cause.getMessage().lastIndexOf("content:") , cause.getMessage().length())).build();
        }

        return new ForecastResponse().builder().message(cause.getMessage().substring(
                cause.getMessage().lastIndexOf("content:") , cause.getMessage().length())).build();
    }

}
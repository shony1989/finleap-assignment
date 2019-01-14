package com.finleap.weather.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@AllArgsConstructor
public class DateCheckerServiceImpl implements DateCheckerService {

    private static final int MAX_DAY = 3;
    private static final int MAX_HOUR = 18;
    private static final int MIN_HOUR = 6;

    public boolean isDateInRange(long epochSecond) {

        LocalDateTime maxLocalDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));

        LocalDateTime resLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.of("UTC"));

        return resLocalDateTime.getDayOfYear() <= maxLocalDateTime.plusDays(MAX_DAY).getDayOfYear();


    }

    public boolean isTimeInRange(long epochSecond) {

        LocalDateTime resLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.of("UTC"));

        return resLocalDateTime.getHour() > MIN_HOUR && resLocalDateTime.getHour() <= MAX_HOUR;
    }
}

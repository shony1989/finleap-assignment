package com.finleap.weather.service;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateCheckerServiceTest {

   @Autowired
   private DateCheckerService service;

    @Test
    public void testIsDateInRangeSuccess() {

        assertThat(service.isDateInRange(Instant.now().getEpochSecond())).as("Success - expected true").isEqualTo(true);
    }

    @Test
    public void testIsDateInRangeFailure() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));

        assertThat(service.isDateInRange(localDateTime.plusDays(4).toEpochSecond(ZoneOffset.UTC))).as("Success - expected false")
                .isEqualTo(false);
    }

    @Test
    public void testIsTimeInRangeSuccess() {
        LocalDateTime ldt = LocalDateTime.of(2019,1,12, 12, 00, 00);
        assertThat(service.isTimeInRange(ldt.toEpochSecond(ZoneOffset.UTC))).as("Success - expected true").isEqualTo(true);
    }

    @Test
    public void testIsTimeInRangeFailure() {
        LocalDateTime ldt = LocalDateTime.of(2019,1,12, 21, 00, 00);
        assertThat(service.isTimeInRange(ldt.toEpochSecond(ZoneOffset.UTC))).as("Success - expected false").isEqualTo(false);
    }
}

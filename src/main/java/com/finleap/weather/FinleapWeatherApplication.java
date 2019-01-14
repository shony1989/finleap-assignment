package com.finleap.weather;

import com.finleap.weather.domainObject.CityDO;
import com.finleap.weather.repo.CityRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Finleap Weather app Start
 */
@EnableCaching
@EnableFeignClients
@EnableHystrix
@SpringBootApplication
@Slf4j
public class FinleapWeatherApplication  {

    @Autowired
    private CityRepository cityRepo;

    public static void main(String[] args) {

        //Specifying the properly willl increase parallel stream stream.
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
        SpringApplication.run(FinleapWeatherApplication.class, args);

    }


    /**
     * The Idea is to Build a Cache Store to store valid cities.
     * @param args
     */
   // @Override
    public void run(String... args) {
        log.info("Application Started !!");

        try {


            String cityListJson = new Scanner(new File("city.json")).useDelimiter("\\Z").next();

            Gson gson = new Gson();

            List<?> cityList = gson.fromJson(cityListJson, List.class);

            cityList.parallelStream().
                    forEach(data -> {
                        String cityJson = String.valueOf(data);
                        CityDO cityDO = gson.fromJson(cityJson, CityDO.class);
                        cityRepo.save(cityDO);

                    });

            log.info("", "data inserted in memory");

        } catch (Exception e) {

            log.error("", e);

        }
    }

}


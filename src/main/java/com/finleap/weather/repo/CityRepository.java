package com.finleap.weather.repo;

import com.finleap.weather.domainObject.CityDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityDO, Long> {
}

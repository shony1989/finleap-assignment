package com.finleap.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastMain {
	
	private BigDecimal temp;
	private BigDecimal pressure;

}

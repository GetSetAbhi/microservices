package com.microservices.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.model.CurrencyConversionBean;

@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean convertCurrency(
			@PathVariable String from, 
			@PathVariable String to);
}

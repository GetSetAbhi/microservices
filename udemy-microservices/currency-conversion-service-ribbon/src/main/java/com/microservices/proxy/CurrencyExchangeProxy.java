package com.microservices.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.model.CurrencyConversionBean;


/**
 * Below annotations are disabled after enabling zuul gateway
 * otherwise use these annotations when normal feign + eureka is configured
 * @FeignClient(name = "currency-exchange-service")
 * @GetMapping("/currency-exchange/from/{from}/to/{to}")
 * */
@FeignClient(name="netflix-zuul-api-gateway-server")
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean convertCurrency(
			@PathVariable String from, 
			@PathVariable String to);
}

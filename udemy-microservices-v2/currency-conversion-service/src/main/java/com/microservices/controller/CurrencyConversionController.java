package com.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.model.CurrencyConversionBean;

@RestController
@RequestMapping(value = "/currency-conversion")
public class CurrencyConversionController {

	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {

		// Feign - Problem 1
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
	
	/*@GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyF(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = currencyExchangeProxy.convertCurrency(from, to);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}*/
}

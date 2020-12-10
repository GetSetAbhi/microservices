package com.example.restfulwebservices.controller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulwebservices.model.SomeBean;
import com.example.restfulwebservices.model.SomeBean2;
import com.example.restfulwebservices.model.SomeBean3;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@RequestMapping(path = "/somebean", method = RequestMethod.GET)
	public SomeBean retrieveSomeBean() {
		return new SomeBean("F1", "F2", "F3");
	}
	
	@RequestMapping(path = "/somebean2", method = RequestMethod.GET)
	public SomeBean2 retrieveSomeBean2() {
		return new SomeBean2("F1", "F2", "F3");
	}
	
	/**
	 * Dynamic Filtering Example
	 * 
	 * "F2" field of SomeBean3 will be omitted from response
	 * 
	 * **/
	@RequestMapping(path = "/somebean3", method = RequestMethod.GET)
	public MappingJacksonValue retrieveSomeBean3() {
		
		SomeBean3 someBean = new SomeBean3("F1", "F2", "F3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("f1", "f2");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBean);

		mapping.setFilters(filters);

		return mapping;
	}
}

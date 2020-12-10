package com.example.restfulwebservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulwebservices.model.Hello;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(value="/hello", produces = MediaType.TEXT_PLAIN_VALUE)
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping(path="/hellobean/{name}")
	public Hello helloBean(@PathVariable(name = "name") String name) {
		return new Hello(name);
	}
	
	/**
	 * Go to postman, enter url http://localhost:8080/hello-i18n
	 * then switch to headers tab and set value of header "Accept-Language"
	 * to either fr, nl or en
	 * 
	 * **/
	@GetMapping(path = "/hello-i18n")
	public String helloWorldInternationalized() {
		// Locale value comes from "Accept-Language" header in the request
		
		return messageSource.getMessage("good.morning.message", null, 
									LocaleContextHolder.getLocale());
	}

}

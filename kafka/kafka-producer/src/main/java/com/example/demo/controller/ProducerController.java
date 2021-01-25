package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProducerService;

/**
 * 
 * http://localhost:8080/kafka/publish?message=<ANY_MESSAGE>
 * 
 * to send a message
 * 
 * **/
@RestController
@RequestMapping(value = "/kafka")
public class ProducerController {
	
	private final ProducerService producer;

    @Autowired
    ProducerController(ProducerService producer) {
        this.producer = producer;
    }
    
    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }

}

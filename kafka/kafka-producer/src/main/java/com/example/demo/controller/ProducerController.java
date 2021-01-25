package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProducerService;
import com.example.models.User;

/**
 * 
 * http://localhost:8081/kafka/publish?message=<ANY_MESSAGE>
 * 
 * to send a message
 * 
 * **/
@RestController
@RequestMapping(value = "/kafka")
public class ProducerController {
	
	private final ProducerService producerService;

    @Autowired
    ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }
    
    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producerService.sendMessage(message);
    }
    
    @PostMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void sendUserToKafkaTopic(@RequestBody User user) {
        this.producerService.sendUser(user);
    }

}

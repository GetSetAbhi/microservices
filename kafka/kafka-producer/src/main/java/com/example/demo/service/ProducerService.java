package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.models.User;


@Service
public class ProducerService {

    private static final String TOPIC = "hybris-messages";
    
    private static final String USER_TOPIC = "hybris-user";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    private KafkaTemplate<String, User> kafkaUserTemplate;

    public void sendMessage(String message) {
        System.out.println(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
    
    public void sendUser(User user) {
        System.out.println(String.format("#### -> Producing message -> %s", user.getFirstName()));
        this.kafkaUserTemplate.send(USER_TOPIC, user);
    }
}

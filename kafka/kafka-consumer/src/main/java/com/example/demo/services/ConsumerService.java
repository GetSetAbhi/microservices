package com.example.demo.services;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;

@Service
public class ConsumerService {
	
	private static final String TOPIC = "hybris-messages";
	private static final String USER_TOPIC = "hybris-user";
	private static final String GROUP_ID = "hybris";
	private static final String USERS_GROUP_ID = "userrs";

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consume(String message) throws IOException {
        System.out.println(String.format("#### -> Consumed message -> %s", message));
    }
    
    @KafkaListener(topics = USER_TOPIC, groupId = GROUP_ID, containerFactory = "kafkaUserListenerContainerFactory")
    public void consumeUser(User user) throws IOException {
        System.out.println(String.format("#### -> Received User -> %s", user.getFirstName()));
    }
}

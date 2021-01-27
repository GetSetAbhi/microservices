package com.example.demo.services;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.google.gson.Gson;

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
    
    /*@KafkaListener(topics = USER_TOPIC, groupId = GROUP_ID, containerFactory = "kafkaUserListenerContainerFactory")
    public void consumeUser(User user) throws IOException {
        System.out.println(String.format("#### -> Received User -> %s", user.getFirstName()));
    }*/
    
    @KafkaListener(topics = USER_TOPIC, groupId = GROUP_ID)
    public void consumeUser(ConsumerRecord<String, String> record) throws IOException {
        //System.out.println(String.format("#### -> Received User -> %s", user.getFirstName()));
    	System.out.println("###########33" + record.toString());
    	User user = new Gson().fromJson(record.value(), User.class);
    	System.out.println("###########33" + user.getFirstName());
    }
}

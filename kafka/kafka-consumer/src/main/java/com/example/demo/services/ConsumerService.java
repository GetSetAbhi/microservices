package com.example.demo.services;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
	
	private static final String TOPIC = "hybris-messages";
	private static final String GROUP_ID = "hybris";

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consume(String message) throws IOException {
        System.out.println(String.format("#### -> Consumed message -> %s", message));
    }
}

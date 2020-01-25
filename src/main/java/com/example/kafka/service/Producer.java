package com.example.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafka.domain.User;

@Service
public class Producer {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private static final String TOPIC = "test";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/*
	 * public void sendMessage(String message) { logger.info("Producer Message: {}",
	 * message); kafkaTemplate.send(TOPIC, message); }
	 */
	
	public void sendMessage(String topicName,User user) {
		logger.info("Topic Name: {}",topicName);
		logger.info("Message to be sent: {}",user.getUserId());
		kafkaTemplate.send(topicName, String.valueOf(user.getUserId()));
	}
}

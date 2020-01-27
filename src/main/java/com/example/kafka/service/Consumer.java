package com.example.kafka.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kafka.client.EmailClient;
import com.example.kafka.domain.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Consumer {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@Value("${user.topic.name}")
	private String userTopic;

	@Value("${username.value}")
	private String username;

	@Autowired
	private EmailClient emailClient;

	@KafkaListener(topics = "user", groupId = "group_id")
	public void sendMessage(String userData) throws JsonParseException, JsonMappingException, IOException {
		logger.info("Consumed Message : {}", userData);
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(userData, User.class);
		logger.info("User: {}", user);
		emailClient.sendEmail(user);
	}
}

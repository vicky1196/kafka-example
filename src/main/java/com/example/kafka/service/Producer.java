package com.example.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafka.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Producer {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String topicName, User user) throws JsonProcessingException {
		
		  logger.info("Topic Name: {}",topicName);
		  logger.info("Message to be sent: {}",user.getUserId());
		  ObjectMapper objectMapper = new ObjectMapper();
		  String jsonString = objectMapper.writeValueAsString(user);
		  logger.info("Json String: {}",jsonString);
		  kafkaTemplate.send(topicName, jsonString);
	}
}

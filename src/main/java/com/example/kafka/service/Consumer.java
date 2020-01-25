package com.example.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kafka.client.EmailClient;
import com.example.kafka.domain.User;
import com.example.kafka.repo.UserRepo;

@Service
public class Consumer {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@Value("${user.topic.name}")
	private String userTopic;
	
	@Value("${username.value}")
	private String username;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailClient emailClient;

	@KafkaListener(topics = "user", groupId = "group_id")
	public void sendMessage(String userId) {
		logger.info("Consumed Message : {}", userId);
		User user = userRepo.findById(Long.parseLong(userId)).get();
		logger.info("User: {}",user);
		emailClient.sendEmail(user);
	}
}

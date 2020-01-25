package com.example.kafka.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.kafka.domain.User;
import com.example.kafka.repo.UserRepo;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Value("${user.topic.name}")
	private String userTopic;

	@Autowired
	UserRepo userRepo;

	@Autowired
	Producer producer;

	@Transactional
	public String addUser(User user) {
		logger.info("New User: {}", user);
		User user1 = userRepo.save(user);
		producer.sendMessage(userTopic, user1);
		return "SUCCESS";
	}

}

package com.example.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.domain.User;
import com.example.kafka.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class KafkaController {

	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user) throws DataAccessException, JsonProcessingException {
		logger.info("New user request received");
		return ResponseEntity.ok(userService.addUser(user));
	}

}

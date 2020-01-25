package com.example.kafka.client;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.kafka.domain.User;

@Component
public class EmailClient {

	@Value("${username.value}")
	private String username;

	@Value("${password.value}")
	private String password;
	
	@Autowired
	Properties props;

	public static final Logger logger = LoggerFactory.getLogger(EmailClient.class);
	
	public void sendEmail(User user) {

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmailId()));
			message.setSubject("New User Added: "+user.getName());
			StringBuffer text = new StringBuffer();
			text.append("Dear "+user.getName()).append("\n");
			text.append("You have been added to system.\n");
			text.append("Your gender: "+user.getGender()).append("\n");
			text.append("This email is sent using Kafka");
			logger.info("Message Body: {}",text.toString());
			message.setText(text.toString());
			Transport.send(message);

			logger.info("Email sent to: "+user.getEmailId());

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

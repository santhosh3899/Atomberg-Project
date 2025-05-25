package com.atomberg.May.Project.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	public void sendPlainEmail(String fromEmail, String toEmail, String mailBody, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(mailBody);
		javaMailSender.send(message);
	}

}

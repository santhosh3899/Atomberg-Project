package com.atomberg.May.Project.controller.service;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	@Autowired
	public TemplateEngine templateEngine;
	
	public void sendPlainEmail(String fromEmail, String toEmail, String subject, String mailBody) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(mailBody);
		javaMailSender.send(message);
	}
	
	public void sendHtmlEmail(String fromEmail, String toEmail, String subject, String mailBody) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.setText(mailBody,true);
		javaMailSender.send(message);
	}
	
	public void sendTemplateEmail(String fromEmail, String toEmail, String subject, String fileName) throws MessagingException {
		
		Context context = new Context();
		
		String mailBody = templateEngine.process(fileName, context);
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.setText(mailBody,true);
		javaMailSender.send(message);
		
	}
	
	public void sendEmailAfterSignup(String fromEmail, String toEmail, String subject, String mailBody ) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(mailBody);
		
		javaMailSender.send(message);
	}

}

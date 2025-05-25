package com.atomberg.May.Project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atomberg.May.Project.controller.pojo.LoginApiData;
import com.atomberg.May.Project.controller.pojo.SignupApiData;
import com.atomberg.May.Project.controller.service.AuthService;
import com.atomberg.May.Project.controller.service.EmailService;
import com.atomberg.May.Project.entity.User;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired
	public AuthService authService;
	
	@Autowired
	public EmailService emailService;
	
	@PostMapping("create-account")
	public Object createAccount(@Valid@RequestBody SignupApiData signupApiData) throws Exception {
		User savedUser = authService.signupService(signupApiData);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "User account added successfully.");
		responseMap.put("User Data", savedUser);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
	}
	
	@PostMapping("/user-login")
	public ResponseEntity<?> userLogin(@Valid @RequestBody LoginApiData loginApiData) throws Exception{
		User userDetails = authService.userLogin(loginApiData);
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Result", "successs");
		responseMap.put("User Data", userDetails);
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
	}
	
	@GetMapping("/send-email")
	public ResponseEntity<?> sendEmail() throws MessagingException{
		Map<String, String> responseMap = new HashMap<String, String>();
		
		String fromEmail="santhoshk8680@gmail.com";
		String toEmail="santhoshk8680@gmail.com";
		String subject = "Test email";
		String mailbody = "Hello, This is test email from spring boot";
		
		
		  emailService.sendPlainEmail(fromEmail, toEmail, subject, mailbody);
		  
		  subject="Test HTML email"; mailbody = "<!DOCTYPE html>" + "<html>" + "<body>"
		  + "<p>Hello Santhosh,</p>" +
		  "<p>This is a test email sent using <strong>Spring Boot</strong>.</p>" +
		  "<p>Regards,<br/>Your Company</p>" + "</body>" + "</html>";
		  
		  emailService.sendHtmlEmail(fromEmail, toEmail, subject, mailbody);
		 
		
		emailService.sendTemplateEmail(fromEmail, toEmail,subject, "test-email");
		
		responseMap.put("Result", "Success");
		responseMap.put("Message", "Email sent");
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}
		
}

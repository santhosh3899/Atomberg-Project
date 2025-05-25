package com.atomberg.May.Project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atomberg.May.Project.controller.pojo.SignupApiData;
import com.atomberg.May.Project.controller.service.AuthService;
import com.atomberg.May.Project.entity.User;

import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired
	public AuthService authService;
	
	@PostMapping("create-account")
	public Object createAccount(@Valid@RequestBody SignupApiData signupApiData) throws Exception {
		User savedUser = authService.signupService(signupApiData);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "User account added");
		responseMap.put("User Data", savedUser);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
	}
		
}

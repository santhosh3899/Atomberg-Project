package com.atomberg.May.Project.controller.service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atomberg.May.Project.controller.pojo.LoginApiData;
import com.atomberg.May.Project.controller.pojo.SignupApiData;
import com.atomberg.May.Project.entity.User;
import com.atomberg.May.Project.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public EmailService emailService;
	
	
	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public User signupService(SignupApiData signupApiData) throws Exception {
		Optional<User> checkData = userRepository.findByEmail(signupApiData.getEmail());
		
		if(checkData.isPresent()==true) {
			throw new Exception("Email already registered with us, Please login");
		}else {
			
			Map<String, Object> responseMap = new HashMap<String, Object>();
			User newUser = new User();
			newUser.setFirstName(signupApiData.getFirstName());
			newUser.setLastName(signupApiData.getLastName());
			newUser.setMiddleName(signupApiData.getMiddleName());
			newUser.setPassword(passwordEncoder.encode(signupApiData.getPassword()));
			newUser.setConfirmPassword(passwordEncoder.encode(signupApiData.getConfirmPassword()));
			newUser.setMobile(signupApiData.getMobile());
			newUser.setDateOfBirth(signupApiData.getDateOfBirth());
			newUser.setPincode(signupApiData.getPincode());
			newUser.setCountry(signupApiData.getCountry());
			newUser.setEmail(signupApiData.getEmail());
			
			User savedUser = userRepository.save(newUser);
			String mailBody = "Hello "+savedUser.getFirstName()+" "+savedUser.getLastName()+
					"Welcome to Atomberg Technologies!"+
					"Your have successfully signed up with us. Please login using the login page."+
					"Many Thans"+
					"Team"+
					"Atomberg technologies";
		 	emailService.sendEmailAfterSignup("santhoshk8680@gmail.com",savedUser.getEmail(),"Account created in Atomberg!",mailBody);
		 	
		 	return savedUser;
		}
		
	
	}
	
	public User userLogin(LoginApiData loginApiData) throws Exception {
		Optional<User> userCheck = userRepository.findByEmail(loginApiData.getEmail());
		
		if(userCheck.isEmpty()==true) {
			throw new Exception("User account not found with "+loginApiData.getEmail()+". Please sign up!");
		}else {
			User user = userCheck.get();
			if(passwordEncoder.matches(loginApiData.getPassword(), user.getPassword())==true) {
				return user;
			}else {
				throw new Exception("Invalid email or password");
			}
		}
		
	}

}

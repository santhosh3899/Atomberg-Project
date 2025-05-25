package com.atomberg.May.Project.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atomberg.May.Project.controller.pojo.SignupApiData;
import com.atomberg.May.Project.entity.User;
import com.atomberg.May.Project.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	public UserRepository userRepository;
	
	
	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public User signupService(SignupApiData signupApiData) throws Exception {
		Optional<User> checkData = userRepository.findByEmail(signupApiData.getEmail());
		
		if(checkData.isPresent()==true) {
			throw new Exception("Email already registered with us, Please login");
		}else {
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
			return savedUser;
		}
	}

}

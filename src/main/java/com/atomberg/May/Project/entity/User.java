package com.atomberg.May.Project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String email;
	private String password;
	private String confirmPassword;
	private String country;
	private String pincode;
	private String mobile;
	
	private LocalDateTime createdOn = LocalDateTime.now();
	private Boolean isActive = true;
	private Boolean isEmailVerified = false;
}

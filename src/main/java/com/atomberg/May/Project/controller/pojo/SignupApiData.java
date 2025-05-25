package com.atomberg.May.Project.controller.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupApiData {
	
	
	@NotNull(message = "Name should not be null")
	@NotBlank(message = "Name should be not blank")
	@Size(min = 2, max = 30, message = "Min 3 and Max 30 chacters are allowed")
	private String firstName;
	
	@NotBlank(message = "Last name should not be blank")
	@NotNull(message = "Last name should not be null")
	@Size(min = 2,max = 30, message = "Min 3 and max 30 characters are allowed")
	private String lastName;
	
	private String middleName;
	
	@NotNull(message = "Name should not be null")
	@NotBlank(message = "Name should be not blank")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email")
	private String email;
	
	@NotNull(message = "Name should not be null")
	@NotBlank(message = "Name should be not blank")
	@Size(min = 8, max = 20, message = "Please enter the password between 8 and 20 characters")
	private String password;
	
	@NotNull(message = "confirm password should not be null")
	@NotBlank(message = "confirm password should not be blank")
	@Size(min = 8, max = 20)
	private String confirmPassword;

	
	@NotNull(message = "Name should not be null")
	@NotBlank(message = "Name should be not blank")
	@Size(min = 10, max = 10,message = "Please enter your 10 digit mobile number")
	private String mobile;
	
	
	@NotNull(message = "Date of birth is required")
	@Past(message = "Date of birth must be in the past")
	@PastOrPresent(message = "Date of birth cannot be in the future")
	@JsonFormat(pattern = "d/M/yyyy")
	private LocalDate dateOfBirth;
	
	
	@NotBlank(message = "Pincode is required")
	@NotNull(message = "Pincode should not be null")
	@Pattern(regexp = "\\d{6}", message = "Pincode must be numeric")
	private String pincode;
	
	
	@NotNull(message = "Country should not be null")
	@NotBlank(message = "Country should not be blank")
	private String country;

}

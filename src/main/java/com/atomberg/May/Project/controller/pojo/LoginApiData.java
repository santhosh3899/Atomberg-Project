package com.atomberg.May.Project.controller.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginApiData {
	
	
	@NotBlank(message = "Email should not be blank")
	@NotNull(message = "Email should not be null")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please enter the valid email")
	private String email;
	
	@NotBlank(message = "Password should not be blank")
	@NotNull(message = "Password should not be null")
	@Size(min = 8, max = 20, message = "Password should be between 8 and 20 characters long")
	private String password;

}

package com.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private int id;
	
	@NotEmpty(message="Name can not be blank!!")
	private String name;
	
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email shoul be valid!!, example@gmail.com")
	private String email;
	
	@NotEmpty(message="Password can not be blank!!")
	private String password;
}
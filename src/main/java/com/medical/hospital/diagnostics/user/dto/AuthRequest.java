package com.medical.hospital.diagnostics.user.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
@Data
public class AuthRequest {
	@NotNull @Email @Length(min = 5, max = 50)
	private String email;
	
	@NotNull @Length(min = 5, max = 10)
	private String password;
	
	public AuthRequest(String email,String password) {
		this.email = email;
		this.password = password;
	}
}

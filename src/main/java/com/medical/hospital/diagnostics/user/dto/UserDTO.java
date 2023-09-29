package com.medical.hospital.diagnostics.user.dto;

import lombok.Data;

@Data
public class UserDTO {
	private Integer id;
	private String email;
	public UserDTO(Integer id, String email) {
		this.id = id;
		this.email = email;
	}
}

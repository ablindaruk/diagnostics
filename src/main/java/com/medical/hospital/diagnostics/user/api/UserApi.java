package com.medical.hospital.diagnostics.user.api;

import com.medical.hospital.diagnostics.user.dto.UserDTO;
import com.medical.hospital.diagnostics.user.entity.User;
import com.medical.hospital.diagnostics.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class UserApi {

	@Autowired private UserService service;
	
	@PutMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
		User createdUser = service.save(user);
		URI uri = URI.create("/users/" + createdUser.getId());
		
		UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getEmail());
		
		return ResponseEntity.created(uri).body(userDto);
	}
}

package com.medical.hospital.diagnostics.user.api;

import com.medical.hospital.diagnostics.jwt.JwtTokenFilter;
import com.medical.hospital.diagnostics.jwt.JwtTokenUtil;
import com.medical.hospital.diagnostics.user.dto.AuthRequest;
import com.medical.hospital.diagnostics.user.dto.AuthResponse;
import com.medical.hospital.diagnostics.user.dto.StartDTO;
import com.medical.hospital.diagnostics.user.entity.User;
import com.medical.hospital.diagnostics.user.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.medical.hospital.diagnostics.interfaces.StartConstants.*;

@RestController
public class AuthApi {
	@Autowired AuthenticationManager authManager;
	@Autowired
	JwtTokenUtil jwtUtil;
	@Autowired
	JwtTokenFilter jwtTokenFilter;
	@Autowired
	AuthService authService;

	@GetMapping("/start")
	public ResponseEntity<?> start(HttpServletRequest request) {
		if (request.getHeader("Authorization") != null) {
			StartDTO startDTO = authService.chooseUserLinks(request);
			return ResponseEntity.ok().body(startDTO);
		}
		else {
			StartDTO startDTO = new StartDTO();
			startDTO.setTitle(TITLE_LOGIN_REQUIRED);
			startDTO.setLink_resource_one(URL_START);
			startDTO.setLink_resource_two(URL_LOGIN);
			return ResponseEntity.ok().body(startDTO);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}

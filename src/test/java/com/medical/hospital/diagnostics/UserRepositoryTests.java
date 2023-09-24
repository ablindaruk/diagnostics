package com.medical.hospital.diagnostics;

import com.medical.hospital.diagnostics.interfaces.UserRepository;
import com.medical.hospital.diagnostics.user.entity.Role;
import com.medical.hospital.diagnostics.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired private UserRepository repo;
	@Test
	public void testCreateUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("guatemala");
		
		User newUser = new User("doctor@gmail.com", password);
		User savedUser = repo.save(newUser);
		
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testAssignRoleToUser() {
		Integer userId = 1;
		Integer roleId = 2;
		User user = repo.findById(userId).get();
		user.addRole(new Role(roleId));
		
		User updatedUser = repo.save(user);
		assertThat(updatedUser.getRoles()).hasSize(1);
		
	}
}

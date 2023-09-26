package com.medical.hospital.diagnostics;

import com.medical.hospital.diagnostics.interfaces.RoleRepository;
import com.medical.hospital.diagnostics.user.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.List;

import static com.medical.hospital.diagnostics.interfaces.StartConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	@Autowired private RoleRepository repo;

	/* Create roles for the project */
	@Test
	public void testCreateRoles() {
		Role admin = new Role(ROLE_ADMIN); //if required
		Role doctor = new Role(ROLE_DOCTOR);
		Role manager = new Role(ROLE_MANAGER);
		repo.saveAll(List.of(admin, doctor, manager));
		
		long count = repo.count();
		assertEquals(3, count);
	}
}

package com.medical.hospital.diagnostics.interfaces;

import com.medical.hospital.diagnostics.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}

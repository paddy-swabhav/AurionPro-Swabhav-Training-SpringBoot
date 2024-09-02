package com.techlabs.jwtsecured.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.jwtsecured.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByRolename(String role);
}

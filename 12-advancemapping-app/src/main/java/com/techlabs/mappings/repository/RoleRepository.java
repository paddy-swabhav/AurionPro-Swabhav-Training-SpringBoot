package com.techlabs.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.mappings.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}

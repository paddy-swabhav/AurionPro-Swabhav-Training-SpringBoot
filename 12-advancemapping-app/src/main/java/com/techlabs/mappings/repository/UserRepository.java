package com.techlabs.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.mappings.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

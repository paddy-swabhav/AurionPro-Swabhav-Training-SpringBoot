package com.techlab.mapipngapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User  findByProfile(Profile profile);

}

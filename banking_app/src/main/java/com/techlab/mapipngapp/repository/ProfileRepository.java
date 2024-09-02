package com.techlab.mapipngapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
	Profile findByEmailId(String email);

}

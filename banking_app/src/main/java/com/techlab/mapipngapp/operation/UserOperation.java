package com.techlab.mapipngapp.operation;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.dto.UserDto;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.repository.ProfileRepository;
import com.techlab.mapipngapp.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserOperation {
	
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private UserRepository userRepository;
	
	public User isUserExist(int userId) {
		
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()) {
			throw new RuntimeException("User Not Exist with Id: "+userId);
		}
		
		return user.get();
		
	}
	
	
	public UserDto toUserDtoMapper(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setRole(user.getRole());
		userDto.setProfile(user.getProfile());
		
		return userDto;
	}
	
	public boolean isEmailExist(String email) {
		if(profileRepository.findByEmailId(email)!=null) {
			throw new RuntimeException("email already exist");
		}
		
		return false;
	}

}

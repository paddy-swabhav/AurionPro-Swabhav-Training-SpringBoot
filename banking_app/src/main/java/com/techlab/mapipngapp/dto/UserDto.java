package com.techlab.mapipngapp.dto;

import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class UserDto {
	 
	    private int userId;



	   
	    private Role role;

	   
	    private Profile profile;
}

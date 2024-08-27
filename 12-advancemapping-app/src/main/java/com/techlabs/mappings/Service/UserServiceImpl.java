package com.techlabs.mappings.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.UserDto;
import com.techlabs.mappings.entity.Role;
import com.techlabs.mappings.entity.User;
import com.techlabs.mappings.repository.RoleRepository;
import com.techlabs.mappings.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public UserDto addUser(UserDto userDto) {
		
		User user = toUserMapper(userDto);
		return toUserDtoMapper(userRepo.save(user));
	}
	
	public User toUserMapper(UserDto userDto)
	{
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	public UserDto toUserDtoMapper(User user)
	{
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setPassword(user.getPassword());
		
		return userDto;
	}

	@Override
	public User assignRoleToUser(int userId, List<Integer> roleId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new NullPointerException("User Not Found"));
		
		List<Role> existtingRoles = user.getRoles();
		if(existtingRoles==null)
			existtingRoles= new ArrayList<>();
		
		List<Role> rolesToAdd = new ArrayList<>();
		
		roleId.forEach((id)->{
			
			Role role = roleRepo.findById(id).orElseThrow(()->new NullPointerException("Role Not Found"));
			
			List<User> existingUsers = role.getUsers();
			if(existingUsers==null)
				existingUsers = new ArrayList<>();
			
			existingUsers.add(user);
			rolesToAdd.add(role);
		});
		
		existtingRoles.addAll(rolesToAdd);
		user.setRoles(existtingRoles);
		
		return userRepo.save(user);
	}

}

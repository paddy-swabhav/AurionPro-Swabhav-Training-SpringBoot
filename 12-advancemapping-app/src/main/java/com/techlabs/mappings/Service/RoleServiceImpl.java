package com.techlabs.mappings.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.RoleDto;
import com.techlabs.mappings.entity.Role;
import com.techlabs.mappings.entity.User;
import com.techlabs.mappings.repository.RoleRepository;
import com.techlabs.mappings.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public RoleDto addRole(RoleDto roleDto) {
		
		Role role = toRoleMapper(roleDto);
		return toRoleDtoMapper(roleRepo.save(role));
	}

	public RoleDto toRoleDtoMapper(Role role)
	{
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(role.getRoleId());
		roleDto.setRoleName(role.getRoleName());
		
		return roleDto;
	}
	
	public Role toRoleMapper(RoleDto roleDto)
	{
		Role role = new Role();
		role.setRoleName(roleDto.getRoleName());
		
		return role;
	}

	@Override
	public Role assignUserToRole(List<Integer> userId, int roleId) {

		Role role = roleRepo.findById(roleId).orElseThrow(()-> new NullPointerException("User Not Found"));
		
		List<User> existtingusers = role.getUsers();
		if(existtingusers==null)
			existtingusers= new ArrayList<>();
		
		List<User> usersToAdd = new ArrayList<>();
		
		userId.forEach((id)->{
			
			User user = userRepo.findById(id).orElseThrow(()->new NullPointerException("Role Not Found"));
			
			List<Role> existingRoles = user.getRoles();
			if(existingRoles==null)
				existingRoles = new ArrayList<>();
			
			existingRoles.add(role);
			usersToAdd.add(user);
		});
		
		existtingusers.addAll(usersToAdd);
		role.setUsers(existtingusers);
		
		return roleRepo.save(role);
	}
}

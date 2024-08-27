package com.techlabs.mappings.Service;

import java.util.List;

import com.techlabs.mappings.dto.UserDto;
import com.techlabs.mappings.entity.User;

public interface UserService {

	UserDto addUser(UserDto userDto);
	User assignRoleToUser(int userId, List<Integer> roleId);
}

package com.techlabs.mappings.Service;

import java.util.List;

import com.techlabs.mappings.dto.RoleDto;
import com.techlabs.mappings.entity.Role;

public interface RoleService {

	RoleDto addRole(RoleDto roleDto);

	Role assignUserToRole(List<Integer> userId, int roleId);
}

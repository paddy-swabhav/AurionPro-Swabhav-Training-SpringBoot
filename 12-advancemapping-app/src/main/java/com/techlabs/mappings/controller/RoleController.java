package com.techlabs.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mappings.Service.RoleService;
import com.techlabs.mappings.dto.RoleDto;
import com.techlabs.mappings.entity.Role;

@RestController
@RequestMapping("/studentapp")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/role")
	public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto role)
	{
		return ResponseEntity.ok(roleService.addRole(role));
	}
	
	@PutMapping("/role")
	public ResponseEntity<Role> assignUserToRole(@RequestParam int roleId, @RequestBody List<Integer> userId)
	{
		return ResponseEntity.ok(roleService.assignUserToRole(userId, roleId));
	}

}

package com.techlabs.bank.service;

import java.util.List;

import com.techlabs.bank.dto.AdminDto;

public interface AdminService {
    AdminDto addAdmin(AdminDto adminDto);
//    AdminDto getAdminByEmailAndPassword(String email, String password);
    List<AdminDto> getAllAdmins();
	AdminDto assignUserToAdmin(int adminId, int userId);
}

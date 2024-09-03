package com.techlabs.bank.service;

import com.techlabs.bank.dto.AdminDto;
import com.techlabs.bank.dto.PageResponse;

public interface AdminService {
    AdminDto addAdmin(AdminDto adminDto);
    
//    AdminDto getAdminByEmailAndPassword(String email, String password);
    
    PageResponse<AdminDto> getAllAdmins(int pagenumber, int pagesize);
    
	AdminDto assignUserToAdmin(int adminId, int userId);
}

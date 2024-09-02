package com.techlabs.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.AdminDto;
import com.techlabs.bank.entity.Admin;
import com.techlabs.bank.repository.AdminRepository;
import com.techlabs.bank.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminDto addAdmin(AdminDto adminDto) {
        Admin admin = toAdmin(adminDto);
        Admin savedAdmin = adminRepository.save(admin);
        return toAdminDto(savedAdmin);
    }

//    @Override
//    public AdminDto getAdminByEmailAndPassword(String email, String password) {
//        Admin admin = adminRepository.findByEmailAndPassword(email, password);
//        if (admin != null) {
//            return toAdminDto(admin);
//        } else {
//            return null; //  throwing an exception 
//        }
//    }

    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminDto> adminDtos = new ArrayList<>();
        admins.forEach(admin -> adminDtos.add(toAdminDto(admin)));
        return adminDtos;
    }
    
    // Mapper methods
    private Admin toAdmin(AdminDto adminDto) {
        
    	Admin admin = new Admin();
    	
    	admin.setAdminId(adminDto.getAdminId());
    	admin.setEmail(adminDto.getEmail());
    	admin.setName(adminDto.getName());
    
    	
    	return admin;
    }

    private AdminDto toAdminDto(Admin admin) {
    	AdminDto adminDto = new AdminDto();
    	
    	adminDto.setAdminId(admin.getAdminId());
    	adminDto.setEmail(admin.getEmail());
    	adminDto.setName(admin.getName());
    
    	
    	return adminDto;
    }
}

package com.techlabs.bank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.AdminDto;
import com.techlabs.bank.entity.Admin;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.repository.AdminRepository;
import com.techlabs.bank.repository.UserRepository;
import com.techlabs.bank.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private UserRepository userRepository;

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
    
    @Override
    public AdminDto assignUserToAdmin(int adminId, int userId) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isEmpty()) {
            throw new RuntimeException("Admin not found with ID: " + adminId);
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        Admin admin = adminOptional.get();
        User user = userOptional.get();
        admin.setUser(user);  // Assign the User to the Admin

        Admin updatedAdmin = adminRepository.save(admin);  // Save the updated Admin entity
        return toAdminDto(updatedAdmin);
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

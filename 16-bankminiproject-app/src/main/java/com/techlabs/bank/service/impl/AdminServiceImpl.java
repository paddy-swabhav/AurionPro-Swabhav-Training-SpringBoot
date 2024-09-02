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

    @Override
    public AdminDto getAdminByEmailAndPassword(String email, String password) {
        Admin admin = adminRepository.findByEmailAndPassword(email, password);
        if (admin != null) {
            return toAdminDto(admin);
        } else {
            return null; //  throwing an exception 
        }
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminDto> adminDtos = new ArrayList<>();
        admins.forEach(admin -> adminDtos.add(toAdminDto(admin)));
        return adminDtos;
    }
    
    // Mapper methods
    private Admin toAdmin(AdminDto adminDto) {
        if (adminDto == null) {
            return null;
        }
        return new Admin(
            adminDto.getAdminId(),
            adminDto.getEmail(),
            adminDto.getPassword(),
            adminDto.getName()
        );
    }

    private AdminDto toAdminDto(Admin admin) {
        if (admin == null) {
            return null;
        }
        return new AdminDto(
            admin.getAdminId(),
            admin.getEmail(),
            admin.getPassword(),
            admin.getName()
        );
    }
}

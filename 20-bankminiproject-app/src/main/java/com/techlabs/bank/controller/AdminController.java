package com.techlabs.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.AdminDto;
import com.techlabs.bank.service.AdminService;

@RestController
@RequestMapping("/bank/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDto> addAdmin(@RequestBody AdminDto adminDto) {
        AdminDto savedAdmin = adminService.addAdmin(adminDto);
        return ResponseEntity.ok(savedAdmin);
    }

//    @GetMapping("/login")
//    public ResponseEntity<AdminDto> getAdminByEmailAndPassword(
//													            @RequestParam String email, 
//													            @RequestParam String password) {
//        AdminDto adminDto = adminService.getAdminByEmailAndPassword(email, password);
//        if (adminDto != null) {
//            return ResponseEntity.ok(adminDto);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
    @GetMapping
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }
}

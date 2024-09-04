package com.techlabs.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.AdminDto;
import com.techlabs.bank.dto.PageResponse;
import com.techlabs.bank.service.AdminService;

@RestController
@RequestMapping("/bank/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

	@PreAuthorize("hasRole('ADMIN')") 
    @PostMapping
    public ResponseEntity<AdminDto> addAdmin(@RequestBody AdminDto adminDto) {
        AdminDto savedAdmin = adminService.addAdmin(adminDto);
        return ResponseEntity.ok(savedAdmin);
    }
    
	@PreAuthorize("hasRole('ADMIN')") 
    @PutMapping
    public ResponseEntity<AdminDto> assignUserToAdmin(@RequestParam int adminId, @RequestParam int userId)
    {
    	return ResponseEntity.ok(adminService.assignUserToAdmin(adminId, userId));
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
    
	@PreAuthorize("hasRole('ADMIN')") 
    @GetMapping
    public ResponseEntity<PageResponse<AdminDto>> getAllAdmins(@RequestParam(defaultValue = "0") int pagenumber,@RequestParam(defaultValue = "10") int pagesize) {

        return ResponseEntity.ok(adminService.getAllAdmins(pagenumber, pagesize));
    }
	

}

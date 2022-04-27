package com.enterarte.controllers;

import com.enterarte.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_TEACHER')")
public class AdminController {
    
    
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;  
    }
       
    @GetMapping("/panel")
    public String home(){
        return "/admin/control-panel";
    }
}

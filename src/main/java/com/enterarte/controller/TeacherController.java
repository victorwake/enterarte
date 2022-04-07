package com.enterarte.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/teacher")
@PreAuthorize("hasAnyRole('ROLE_PROFESOR')")
public class TeacherController {
    
    
    @GetMapping("/panel")
    public String home(){
        return "/teacher/control-panel";
    }
    

}

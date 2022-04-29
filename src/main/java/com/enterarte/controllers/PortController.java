package com.enterarte.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class PortController {
    
    @GetMapping("/")
    public String home(){
        return "index";
    }
    
}

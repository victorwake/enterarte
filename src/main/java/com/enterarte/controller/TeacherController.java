package com.enterarte.controller;

import com.enterarte.Service.WorkshopService;
import com.enterarte.entity.Workshop;
import com.enterarte.mistakes.ErrorService;
import com.enterarte.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller
@RequestMapping("/teacher")
@PreAuthorize("hasRole('ROLE_PROFESOR') OR hasRole('ROLE_ADMIN')")
public class TeacherController {
    
    private final WorkshopService workshopService;
    private final WorkshopRepository worksopRepository;
    
    @Autowired
    public TeacherController(WorkshopService workshopService, WorkshopRepository worksopRepository ){
        this.workshopService = workshopService;
        this.worksopRepository = worksopRepository;
    }

    
    @GetMapping("/panel")
    public String home(){
        return "/teacher/control-panel";
    }
    
    @GetMapping("/form")
    public String createTaller(){
        return "/teacher/create-workshop";
    }
    
    @PostMapping("/create")
    public String saveTaller(@ModelAttribute Workshop workshop, MultipartFile file) throws ErrorService{
      try {
          workshopService.save(workshop, file);
          return "main/main";
      }catch (Exception e) {
//            model.put("error", e.getMessage());

//            model.addAttribute("errorMessage", e.getMessage());
//            System.err.println(e);
            return "/teacher/form";
    }
    
    
    
    
    
    
    }
}

package com.enterarte.controller;

import com.enterarte.Service.CustomerService;
import com.enterarte.Service.WorkshopService;
import com.enterarte.entity.Customer;
import com.enterarte.entity.Workshop;
import com.enterarte.mistakes.ErrorService;
import com.enterarte.repository.WorkshopRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/teacher")
@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
public class TeacherController {

    private final WorkshopService workshopService;
    private final WorkshopRepository worksopRepository;
    private final CustomerService customerservice;

    @Autowired
    public TeacherController(WorkshopService workshopService, WorkshopRepository worksopRepository, CustomerService customerservice) {
        this.workshopService = workshopService;
        this.worksopRepository = worksopRepository;
        this.customerservice = customerservice;
    }

    @GetMapping("/panel")
    public String home() {
        return "/teacher/control-panel";
    }

    @GetMapping("/form")
    public String createTaller(ModelMap model) {
        Workshop workshop = new Workshop();
        model.addAttribute("workshop", workshop);

        return "/teacher/create-workshop";
    }

    @PostMapping("/create/{id}")
    public String saveTaller(@ModelAttribute Workshop workshop, @PathVariable("id") String customerId, MultipartFile file, ModelMap model) throws ErrorService {
        try {
            Customer teacher = customerservice.findById(customerId);
            workshopService.save(workshop, teacher, file);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "/teacher/create-workshop";
        }
        return "/teacher/control-panel";
    }
}

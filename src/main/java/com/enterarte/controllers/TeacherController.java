package com.enterarte.controllers;

import com.enterarte.services.CustomerService;
import com.enterarte.services.WorkshopService;
import com.enterarte.entities.Customer;
import com.enterarte.entities.Workshop;
import com.enterarte.enums.Role;
import com.enterarte.mistakes.ErrorService;
import com.enterarte.repositories.WorkshopRepository;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/teacher")
@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
public class TeacherController {

    private final WorkshopService workshopService;
    private final WorkshopRepository worksopRepository;
    private final CustomerService customerservice;
     private Role role;
    

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
            workshopService.save(workshop, teacher, Optional.ofNullable(file));
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "/teacher/create-workshop";
        }
        return "redirect:/teacher/listartalleres";
    }

    @GetMapping("/listartalleres")
    public String listartalleres(ModelMap model,HttpSession session) {
         try{
        Customer customer = (Customer) session.getAttribute("customersession");
        String idcustomer=customer.getId();
        if(customer.getRole()==role.ADMIN){
            List<Workshop> workshops = workshopService.listarWorkshopActivas();
        model.addAttribute("workshops", workshops);
        
       }else{
       List<Workshop> workshops = workshopService.listWorkshopsteacher(idcustomer);
       model.addAttribute("workshops", workshops);
           
   }
       
         }catch(Exception e){
             model.put("error", e.getMessage());
         }
       return "teacher/listar-workshop";
    }

    @GetMapping("/workshop_baja")
    public String listarWorkshopBaja(ModelMap model ,HttpSession session) {
         try{
        Customer customer = (Customer) session.getAttribute("customersession");
        String idcustomer=customer.getId();
        if(customer.getRole()==role.ADMIN){
            List<Workshop> workshops = workshopService.listarWorkshopBajas();
        model.addAttribute("workshops", workshops);       
       }else{
       List<Workshop> workshops = workshopService.listWorkshopsTeacherBaja(idcustomer);
       model.addAttribute("workshops", workshops);         
   }     
         }catch(Exception e){
             model.put("error", e.getMessage());
         }      
        return "teacher/listar-workshopbaja";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarWorkshop(@PathVariable("id") String workshopid, ModelMap model) {

        try {
            Workshop workshop = workshopService.findById(workshopid); 
            workshopService.DarDeBaja(workshop);
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }
        
        return "redirect:/teacher/listartalleres";
    }
    
    @GetMapping("/alta/{id}")
    public String altaWorkshop(@PathVariable("id") String workshopid, ModelMap model) {

        try {
            Workshop workshop = workshopService.findById(workshopid); 
            workshopService.DarDeAlta(workshop);
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }
        
        return "redirect:/teacher/workshop_baja";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificarplay(@PathVariable("id") String id, ModelMap model) {
        try {
            Workshop workshop = workshopService.findById(id);
            model.addAttribute("workshop", workshop);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "teacher/create-workshop";
    }

}

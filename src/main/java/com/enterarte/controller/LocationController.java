package com.enterarte.controller;

import com.enterarte.Service.LocationService;
import com.enterarte.entity.Location;
import com.enterarte.entity.Play;
import com.enterarte.mistakes.ErrorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller
@RequestMapping("/location")
public class LocationController {
    
    private final LocationService locationService;
    
    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
        
    }
    
    
        //Mapping de la creaciaon de la locacion
    @GetMapping("/form")
    public String createLocation(ModelMap model) {
        model.addAttribute("location", new Location());
        
        return "location/register";
    }
    
    @PostMapping("/form")
    public String saveLocation(@ModelAttribute Location location,ModelMap model) {
        try {
            //validar
           
            locationService.saveLocation(location);
      
        } catch (ErrorService e) {
            model.put("error", e.getMessage());
//            model.addAttribute("errorMessage", e.getMessage());
//            System.err.println(e);
            return "/location/register";
        }
         return "redirect:/admin/panel";
    }
    
}


    
    
    
    
    
    
    
    
    
    
    
    


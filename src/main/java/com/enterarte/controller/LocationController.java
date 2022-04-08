package com.enterarte.controller;

import com.enterarte.Service.LocationService;
import com.enterarte.entity.Location;
import com.enterarte.mistakes.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




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
    public String createLocation(ModelMap model){
        model.addAttribute("location", new Location());
        return "admin/create-location";
    }
    
    @PostMapping("/createLocation")
    public String saveLocation(@ModelAttribute @RequestParam String nombre, String ubicacion) throws ErrorService{
        locationService.saveLocation( nombre, ubicacion);
        return "admin/control-panel";
    }

}

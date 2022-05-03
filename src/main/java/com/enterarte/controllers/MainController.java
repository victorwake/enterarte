package com.enterarte.controllers;

import com.enterarte.services.LocationService;
import com.enterarte.services.PlayService;
import com.enterarte.services.WorkshopService;
import com.enterarte.entities.Play;
import com.enterarte.entities.Workshop;
import com.enterarte.repositories.LocationRepository;
import com.enterarte.repositories.WorkshopRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    
     private final PlayService playService;
    private final LocationRepository locationRepository;
    private final LocationService locationService;
    private final WorkshopService workshopService;
    private final WorkshopRepository workshopRepository;
    
    @Autowired
    public MainController(PlayService playService, LocationService locationService, LocationRepository locationRepository, WorkshopRepository workshopRepository, WorkshopService workshopService) {
        this.playService = playService;
        this.locationService = locationService;
        this.locationRepository = locationRepository;
        this.workshopRepository = workshopRepository;
        this.workshopService = workshopService;
    }
    
    
    
    @GetMapping("/main")
    public String home(ModelMap model){
        List<Play> plays = playService.listarPlaysActivas();
        model.addAttribute("plays", plays);
        
        List<Workshop> workshops = workshopService.listarWorkshopActivas();
        model.addAttribute("workshops", workshops);
        return "/main/main";
    }
    
    @GetMapping("/porfolio/{id}")
    public String porfolioplay(ModelMap model,@PathVariable String id) {    
     
         try {
              Play plays = playService.findById(id);
              model.addAttribute("plays", plays);
         } catch (Exception ex) {
             model.put("error", ex.getMessage());
         }
       
        return "/main/porfolioplay";
    }
    

}

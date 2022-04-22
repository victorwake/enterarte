package com.enterarte.controller;

import com.enterarte.Service.LocationService;
import com.enterarte.Service.PlayService;
import com.enterarte.Service.WorkshopService;
import com.enterarte.entity.Play;
import com.enterarte.entity.Workshop;
import com.enterarte.repository.LocationRepository;
import com.enterarte.repository.WorkshopRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    
    
    @GetMapping("/main{id}")
    public String home(ModelMap model){
        List<Play> plays = playService.listarPlay();
        model.addAttribute("plays", plays);
        
        List<Workshop> workshops = workshopService.listWorkshops();
        model.addAttribute("workshops", workshops);
        return "/main/main";
    }
    
    @GetMapping("/porfolio")
    public String porfolioplay(ModelMap model) {    
        List<Play> plays = playService.listarPlay();
        model.addAttribute("plays", plays);
        return "/main/porfolioplay";
    }
    

}

package com.enterarte.controller;

import com.enterarte.Service.LocationService;
import com.enterarte.Service.PlayService;
import com.enterarte.entity.Play;
import com.enterarte.repository.LocationRepository;
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
    
    @Autowired
    public MainController(PlayService playService, LocationService locationService, LocationRepository locationRepository) {
        this.playService = playService;
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }
    
    
    
    @GetMapping("/main")
    public String home(ModelMap model){
        List<Play> plays = playService.listarPlay();
        model.addAttribute("plays", plays);
        return "/main/main";
    }
    

}

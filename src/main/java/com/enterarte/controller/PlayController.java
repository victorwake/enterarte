
package com.enterarte.controller;

import com.enterarte.Service.LocationService;
import com.enterarte.Service.PlayService;
import com.enterarte.entity.Location;
import com.enterarte.entity.Play;
import com.enterarte.repository.LocationRepository;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/play")
public class PlayController {
    
    private final PlayService playService;
    private final LocationRepository locationRepository;
    private final LocationService locationService;
    
    @Autowired
    public PlayController(PlayService playService, LocationService locationService, LocationRepository locationRepository) {
        this.playService = playService;
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }
        
    ////////////////////////////////////////////////////////////////////////////
    //Muestra la plantilla de registro de un show u obra
    ////////////////////////////////////////////////////////////////////////////
   
    @GetMapping("/form")
    public String createPlay(ModelMap model) {
        model.addAttribute("play", new Play());
        List<Location> locations = locationRepository.findAll();
        model.put("locations", locations);
        return "play/register";
    }
    
    @PostMapping("/create")
    public String savePlay(@ModelAttribute Play play, Location location,  ModelMap model, @RequestParam MultipartFile file) {
        try {
            //validar
            
            playService.save(play, location, file);

            return "redirect:/admin/panel";

        } catch (Exception e) {
            model.put("error", e.getMessage());
//            model.addAttribute("errorMessage", e.getMessage());
//            System.err.println(e);
            return "/play/form";
        }
    }
    
}

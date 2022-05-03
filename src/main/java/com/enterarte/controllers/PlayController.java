package com.enterarte.controllers;

import com.enterarte.entities.Customer;
import com.enterarte.services.LocationService;
import com.enterarte.services.PlayService;
import com.enterarte.entities.Location;
import com.enterarte.entities.Play;
import com.enterarte.repositories.LocationRepository;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/play")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
    
    @GetMapping("/formcrop")
    public String createPlayCrop(ModelMap model) {
        model.addAttribute("play", new Play());
        List<Location> locations = locationRepository.findAll();
        model.put("locations", locations);
        return "play/playregistercrop";
    }
    
    
    @PostMapping("/form")
    public String savePlay(@ModelAttribute Play play, String locationid, @RequestParam MultipartFile file ,ModelMap model) {
        try {
            //validar
            Location location=locationService.buscarPorId(locationid);
            playService.save(play, location,Optional.ofNullable(file));  
        } catch (Exception e) {
            model.put("error", e.getMessage());
            List<Location> locations = locationRepository.findAll();
        model.put("locations", locations);
            return "/play/register";
        }
         return "redirect:/admin/panel";
    }   
    
//    @GetMapping("/list")
//    public String listarPlay(ModelMap model) {
//
//        List<Play> plays = playService.listarPlay();
//        model.addAttribute("plays", plays);
//        
//        return "/play/list-play";
//    }
    
     @GetMapping("/modificar/{id}")
    public String modificarplay(@PathVariable("id") String id, ModelMap model) {
        try {
           Play play = playService.findById(id);
            model.addAttribute("play",play);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "play/register";
    }
    
    @GetMapping("/baja/{id}")
    public String bajaplay(@PathVariable String id,ModelMap model){
        try{
         Play play = playService.findById(id);
         playService.baja(play);
 
        }catch(Exception ex){
            model.addAttribute("error", ex.getMessage());
        }
        
       return "redirect:/play/listar-playactiva";
        
    }
    
    @GetMapping("/alta/{id}")
    public String altaplay(@PathVariable String id,ModelMap model){
        try{
         Play play = playService.findById(id);
         playService.alta(play);
 
        }catch(Exception ex){
            model.addAttribute("error", ex.getMessage());
        }
        
       return "redirect:/play/listar-playbaja";
        
    }
    
    
    
    @GetMapping("/listar-playactiva")
    public String listarPlay(ModelMap model) {

        List<Play> plays = playService.listarPlaysActivas();
        model.addAttribute("plays", plays);
        
        return "play/list-playaltas";
    }
    
    @GetMapping("/listar-playbaja")
    public String listarPlayBaja(ModelMap model) {

        List<Play> plays = playService.listarPlaysBajas();
        model.addAttribute("plays", plays);
        
        return "play/list-playbajas";
    }
}

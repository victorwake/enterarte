
package com.enterarte.controller;

import com.enterarte.Service.PlayService;
import com.enterarte.entity.Play;
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
    
    @Autowired
    public PlayController(PlayService playService) {
        this.playService = playService;
    }
        
    ////////////////////////////////////////////////////////////////////////////
    //Muestra la plantilla de registro de un show u obra
    ////////////////////////////////////////////////////////////////////////////
   
    @GetMapping("/form")
    public String createPlay(ModelMap model) {
        model.addAttribute("play", new Play());
        return "play/register";
    }
    
    @PostMapping("/register")
    public String savePlay(@ModelAttribute Play play, ModelMap model, @RequestParam MultipartFile file) {
        try {
            //validar
            
            playService.save(play,file);

            return "redirect:/main/main";

        } catch (Exception e) {
            model.put("error", e.getMessage());
//            model.addAttribute("errorMessage", e.getMessage());
//            System.err.println(e);
            return "/play/register";
        }
    }
    
}

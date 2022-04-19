package com.enterarte.controller;


import com.enterarte.Service.CustomerService;
import com.enterarte.Service.PlayService;
import com.enterarte.Service.WorkshopService;
import com.enterarte.entity.Customer;
import com.enterarte.entity.Play;
import com.enterarte.entity.Workshop;
import com.enterarte.mistakes.ErrorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/photo")
public class PhotoController {
    
   
    private CustomerService customerService;
    private PlayService playService;
    private WorkshopService workshopService;

    @Autowired
    public PhotoController(CustomerService customerService, PlayService playService, WorkshopService workshopService) {
        this.customerService = customerService;
        this.playService = playService;
        this.workshopService = workshopService;
    }
    
    
    
    
    @GetMapping("customer{id}")
    public ResponseEntity<byte[]> photoUser(@PathVariable String id){
        
        try {
            Customer customer = customerService.findById(id);
            if(customer.getPhoto() == null){
                throw new ErrorService("El usuaio no pose foto");
            }
            byte[] photo = customer.getPhoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
                      
            return new ResponseEntity<>(photo, headers, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(PhotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("play{id}")
    public ResponseEntity<byte[]> photoPlay(@PathVariable String id){
        
        try {
            Play play = playService.findById(id);
            if(play.getPhoto() == null){
                throw new ErrorService("El usuaio no pose foto");
            }
            byte[] photo = play.getPhoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
                      
            return new ResponseEntity<>(photo, headers, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(PhotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("workshop{id}")
    public ResponseEntity<byte[]> photoWorkshop(@PathVariable String id){
        
        try {
            Workshop workshop = workshopService.findById(id);
            if(workshop.getPhoto() == null){
                throw new ErrorService("El taller no tiene foto");
            }
            byte[] photo = workshop.getPhoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
                      
            return new ResponseEntity<>(photo, headers, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(PhotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

}

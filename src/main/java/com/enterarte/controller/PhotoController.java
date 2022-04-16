package com.enterarte.controller;


import com.enterarte.Service.CustomerService;
import com.enterarte.Service.PlayService;
import com.enterarte.entity.Customer;
import com.enterarte.entity.Play;
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
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private PlayService playService;
    
    
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
    

}

package com.enterarte.controllers;

import com.enterarte.entities.Contact;
import com.enterarte.services.ContactService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {
    
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;

    }
    
   
    
   @PostMapping("/save")
   public String saveMessaje(@ModelAttribute Contact contact, ModelMap model){
       try{
       contactService.sendContact(contact);
       }catch(Exception e){
           model.addAttribute("error", e.getMessage());
       }
       return "redirect:/main";
   }
    
    
    
    ////////////////  Listar  ////////////////
    
     @GetMapping("/message")
    public String listarMenssageAlta(ModelMap model) {
        List<Contact> contacts = contactService.listarMenssageActivos();      
        model.addAttribute("contacts", contacts);
        
        return "admin/list-message";
    }

    @GetMapping("/message-baja")
    public String listarMenssageBaja(ModelMap model) {
        List<Contact> contacts = contactService.listarMenssageBajas();
        model.addAttribute("contacts", contacts);
        
        return "admin/list-message-baja";
    }
    
    //////////////// archivar /activar / eliminar ///////////////
    
    @GetMapping("/archivar/{id}")
    public String bajaMessaje(@PathVariable String id, ModelMap model) {
        try {
            Contact contact = contactService.findById(id);
            contactService.archivar(contact);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "redirect:/contact/message";

    }

    @GetMapping("/alta/{id}")
    public String altaMessage(@PathVariable String id, ModelMap model) {
        try {
            Contact contact = contactService.findById(id);
            contactService.darDeAlta(contact);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "redirect:/contact/message-baja";

    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap model) {
        try {
            Contact contact = contactService.findById(id);
            contactService.eliminar(contact);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "redirect:/contact/message";

    }
    
    @GetMapping("/messagefull/{id}")
    public String porfolioPlay(ModelMap model,@PathVariable String id) {    
     
         try {
              Contact contacts = contactService.findById(id);
              model.addAttribute("contacts", contacts);
         } catch (Exception ex) {
             model.put("error", ex.getMessage());
         }
       
        return "/admin/message-full";
    }
    
}
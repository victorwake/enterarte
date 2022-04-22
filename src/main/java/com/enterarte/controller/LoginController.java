package com.enterarte.controller;



import com.enterarte.Service.CustomerService;
import com.enterarte.entity.Customer;
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
@RequestMapping("/login")
public class LoginController {

   CustomerService customerService; 

    @Autowired
    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
   
    
 @GetMapping("/form")
    public String createUser(ModelMap model) {
        model.addAttribute("customer", new Customer());
        return "customer/register";
    }

    @PostMapping("/register")
    public String saveCustomer(@ModelAttribute Customer customer, ModelMap model,
            @RequestParam String clave2, @RequestParam MultipartFile file) {
        try {
            //validar
            customerService.validarClaveX2(customer.getClave(), clave2);
            customerService.save(customer, Optional.ofNullable(file));
//            model.put("titulo", "Bienvenido.");
//            model.put("descripcion", "Usuario registrado con exito.");
            return "redirect:/login/login";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "/customer/register";
        }

    }

    
    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, ModelMap model) {
        if (error != null) {
            model.put("error", "Email o password incorrectos");
        }
        return "/login/login";
    }

}
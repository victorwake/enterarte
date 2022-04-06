package com.enterarte.controller;

import com.enterarte.Service.CustomerService;
import com.enterarte.Service.PhotoService;
import com.enterarte.entity.Customer;
import com.enterarte.entity.Photo;
import com.enterarte.repository.PhotoRepository;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/customer")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN)")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Autowired
    private PhotoService photoService;

    @Autowired
    private PhotoRepository photoRepository;

    ////////////////////////////////////////////////////////////////////////////
    //Mustra la plantilla de registro y registra el usuario el PostMapping
    ////////////////////////////////////////////////////////////////////////////
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

//            model.addAttribute("errorMessage", e.getMessage());
//            System.err.println(e);
            return "/customer/register";
        }

    }

    @PostMapping("/update")
    public String saveupdate(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String dni, @RequestParam String numeroTelefono, ModelMap model,
            MultipartFile file, HttpSession session) {
        try {
            //validar
            Customer customer = (Customer) session.getAttribute("customersession");
            customerService.modificar(nombre, apellido, dni, numeroTelefono, file, customer);
//            model.put("descripcion", "Usuario registrado con exito.");

        } catch (Exception e) {
            model.put("error", e.getMessage());

            model.addAttribute("errorMessage", e.getMessage());
            System.err.println(e);
            return "customer/customeredit";
        }
        return "customer/profile";
    }
//    ////////////////////////////////////////////////////////////////////////////
 @GetMapping("/baja/{id}")
    public String desactivate(@PathVariable String id , ModelMap model) {
        try {
            customerService.desactivate(id);
         
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            
        }
        return "redirect:/logout";
    }

    
    
    @GetMapping("/modificar/{id}")
    public String modificarcustomer(@PathVariable("id") String customerid, ModelMap model) {
        try {
            Customer customer = customerService.findById(customerid);
            model.addAttribute("customer", customer);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "customer/customeredit";
    }
    ////////////////////////////////////////////////////////////////////////////

    @GetMapping("/profile")
    public String profile(ModelMap model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customersession");

        return "customer/profile";
    }

    @GetMapping("/password")
    public String password(ModelMap model) {

        return "customer/password";
    }

    @PostMapping("/password")
    public String profile2(ModelMap model, @ModelAttribute Customer customer, @RequestParam String clave1, @RequestParam String clave2) {
        try {
            //valida que las 2 nuevas claves sean iguales
            customerService.validarClaveX2(clave1, clave2);
            customer.setClave(clave2);

            return "redirect:/customer/profile";

        } catch (Exception e) {
            model.put("error", e.getMessage());

            return "/customer/profile";
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    @GetMapping("/list")
    public String listPublisheds(ModelMap model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customersession");

        List<Customer> customers = customerService.listCustomers();//Traigo la lista de editoriales
        model.addAttribute("customers", customers);//Agrego la lista a ModelMap(model)
        return "customer/list-customer"; //ruta del archivo donde busca para mostrar
    }

    @GetMapping("/load/{id}")
    public ResponseEntity<byte[]> photo(@PathVariable String id) {
        Photo photo = photoRepository.getOne(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(photo.getContenido(), headers, HttpStatus.OK);

    }

   

}

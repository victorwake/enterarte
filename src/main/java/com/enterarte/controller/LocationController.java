package com.enterarte.controller;

import com.enterarte.Service.LocationService;
import com.enterarte.entity.Location;
import com.enterarte.mistakes.ErrorService;
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
@RequestMapping("/location")
@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;

    }

    //Mapping de la creaciaon de la locacion
    @GetMapping("/form")
    public String createLocation(ModelMap model) {
        model.addAttribute("location", new Location());

        return "admin/create-location";
    }

    @PostMapping("/form")
    public String saveLocation(@ModelAttribute Location location, ModelMap model) {
        try {
            //validar
            locationService.saveLocation(location);

        } catch (ErrorService e) {
            model.put("error", e.getMessage());
            return "/location/register";
        }
        return "redirect:/admin/panel";
    }

    @GetMapping("/listar-locacion")
    public String listarlocation(ModelMap model) {

//        Location location = (Location) session.getAttribute("locationsession");
        List<Location> locaciones = locationService.listarlocacionesActivas();      
        model.addAttribute("locaciones", locaciones);
        
        return "location/listar-locaciones";
    }

    @GetMapping("/modificarlocacion/{id}")
    public String modificarlocacion(@PathVariable("id") String locacionid, ModelMap model) {
        System.out.println(locacionid);
        try {

            Location location = locationService.buscarPorId(locacionid);
            model.addAttribute("location", location);
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }

        return "admin/create-location";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarlocacion(@PathVariable("id") String locacionid, ModelMap model) {

        try {
            Location location = locationService.buscarPorId(locacionid); 
            locationService.DarDeBaja(location);
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }
        
        return "redirect:/location/listar-locacion";
    }
}



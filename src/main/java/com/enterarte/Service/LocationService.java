package com.enterarte.Service;

import com.enterarte.entity.Customer;
import com.enterarte.entity.Location;
import com.enterarte.entity.Play;
import com.enterarte.mistakes.ErrorService;
import com.enterarte.repository.LocationRepository;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional
    public void saveLocation(Location location) throws ErrorService {
       validar(location);
        

        locationRepository.save(location);

    }

    public Location buscarPorId(String id) throws Exception {

        Optional<Location> option = locationRepository.findById(id);
        if (option.isPresent()) {
            Location location = option.get();
            return location;
        } else {
            throw new Exception("locacion no encontrada");
        }

    }

    public void validar(Location location) throws ErrorService {
        validaUbicacion(location);
        validaNombre(location);
    }

    public void validaNombre(Location location) throws ErrorService {

        if (location.getNombre().isEmpty()) {
            throw new ErrorService("Tiene que ingresar un nombre");
        }

        Pattern pattern = Pattern
                .compile("^[a-zA-Z\\s]+{3,25}");
        Matcher mather = pattern.matcher(location.getNombre());
        if (mather.find() != true) {
            throw new ErrorService("caracteres invalido nombre.");
        }
    }

    public void validaUbicacion(Location location) throws ErrorService {

        if (location.getUbicacion().isEmpty() || location.getUbicacion() == null) {
            throw new ErrorService("Tiene que ingresar una ubicacion");
        }

        Pattern pattern = Pattern
                .compile("^[a-zA-Z\\s]+{3,25}");
        Matcher mather = pattern.matcher(location.getUbicacion());
        if (mather.find() != true) {
            throw new ErrorService("caracteres invalido para la ubicacion.");
        }
    }

}

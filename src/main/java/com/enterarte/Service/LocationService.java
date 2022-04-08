package com.enterarte.Service;

import com.enterarte.entity.Location;
import com.enterarte.repository.LocationRepository;
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
    public void saveLocation(String nombre, String ubicaion) {
        Location location = new Location();
        location.setNombre(nombre);
        location.setUbicacion(ubicaion);

        locationRepository.save(location);
        
    }

}

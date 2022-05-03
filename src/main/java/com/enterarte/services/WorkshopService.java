package com.enterarte.services;

import com.enterarte.entities.Customer;
import com.enterarte.entities.Photo;
import com.enterarte.entities.Workshop;
import com.enterarte.mistakes.ErrorService;
import com.enterarte.repositories.WorkshopRepository;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class WorkshopService {

    @Autowired
    private PhotoService photoService;

    @Autowired
    public final WorkshopRepository workshopRepository;

    @Autowired
    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public void save(Workshop workshop,Customer teacher, Optional<MultipartFile> file) throws Exception {
       
//      validar(workshop);
        if (file.isPresent() && !file.get().isEmpty()) {
            Photo photo = photoService.guardarFoto(file.get());
            workshop.setPhoto(photo);
        }
        workshop.setTeacher(teacher);
        workshop.setAlta(true);

        workshopRepository.save(workshop);
    }

    @Transactional(rollbackOn = Exception.class)
    public Workshop findById(String id) throws Exception {
        return workshopRepository.findById(id).orElseThrow(() -> new Exception("Workshop no encontrado"));
    }

    @Transactional(rollbackOn = Exception.class)
    public List<Workshop> listWorkshops() {
        return workshopRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    public void validar(Workshop workshop) throws ErrorService {
        if (workshop.getTitulo().isEmpty()) {
            throw new ErrorService("Tiene que ingresar un titulo");
        }

        Pattern pattern = Pattern
                .compile("^[a-zA-Z\\s]+{3,25}");
        Matcher mather = pattern.matcher(workshop.getTitulo());
        if (mather.find() != true) {
            throw new ErrorService("Caracteres invalido para titulo");
        }

    }

    @Transactional(rollbackOn = Exception.class)
    public void modificar(Workshop workshop, MultipartFile file) throws Exception {
        validar(workshop);

        String idPhoto = null;
        if (workshop.getPhoto() != null) {
            idPhoto = workshop.getPhoto().getId();
        }
        Photo photo = photoService.actualizar(idPhoto, file);
        workshop.setPhoto(photo);

        workshopRepository.save(workshop);
    }
       
    @Transactional(rollbackOn = Exception.class)
    public List<Workshop> listWorkshopsteacher(String id) {
        return workshopRepository.FindbyWorkshopAlta(id);
    }
    
    @Transactional(rollbackOn = Exception.class)
    public List<Workshop> listWorkshopsTeacherBaja(String id) {
        return workshopRepository.FindbyWorkshopBaja(id);
    }
    
    
     public void DarDeBaja(Workshop workshop) throws Exception {
       workshop.setAlta(false);
       workshopRepository.save(workshop);
       
    }
     public void DarDeAlta(Workshop workshop) throws Exception {
       workshop.setAlta(true);
       workshopRepository.save(workshop);
       
    }
     
     public List<Workshop> listarWorkshopActivas() {      
      workshopRepository.workshopActivos(Boolean.TRUE); 
        return  workshopRepository.workshopActivos(Boolean.TRUE);
    }

    public List<Workshop> listarWorkshopBajas() {     
      workshopRepository.workshopActivos(Boolean.FALSE);     
        return  workshopRepository.workshopActivos(Boolean.FALSE);
    }

}

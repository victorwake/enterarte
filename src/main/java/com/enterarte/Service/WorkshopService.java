package com.enterarte.Service;

import com.enterarte.entity.Customer;
import com.enterarte.entity.Photo;
import com.enterarte.entity.Workshop;
import com.enterarte.mistakes.ErrorService;
import com.enterarte.repository.WorkshopRepository;
import java.util.List;
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
    public void save(Workshop workshop, MultipartFile file) throws Exception {

//        validar(workshop);

        Photo photo = photoService.guardarFoto(file);
        workshop.setPhoto(photo);

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

}

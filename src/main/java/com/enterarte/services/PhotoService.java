package com.enterarte.services;

import com.enterarte.entities.Photo;
import com.enterarte.mistakes.ErrorService;
import com.enterarte.repositories.PhotoRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoService {

    public final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public Photo guardarFoto(MultipartFile file) throws ErrorService {
        if (file != null) {
            try {
                Photo photo = new Photo();
                photo.setMime(file.getContentType());
                photo.setNombre(file.getName());
                photo.setContenido(file.getBytes());

                return photoRepository.save(photo);
            } catch (Exception e) {
                throw new ErrorService("Error en la foto!");

            }
        }
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    public Photo actualizar(String idPhoto, MultipartFile file) throws ErrorService {
        if (file != null) {
            try {
                Photo photo = new Photo();

                if (idPhoto != null) {
                    Optional<Photo> respuesta = photoRepository.findById(idPhoto);
                    photo = respuesta.get();
                }

                photo.setMime(file.getContentType());
                photo.setNombre(file.getName());
                photo.setContenido(file.getBytes());

                return photoRepository.save(photo);
            } catch (Exception e) {
                throw new ErrorService("Error en la foto!");

            }
        }
        return null;
    }
    

    @Transactional
    public Photo buscar(String Id) throws Error {

        return photoRepository.getOne(Id);
    }
    @Transactional
    public void deletephoto(String id) throws Exception {
        Photo photo = buscar(id);
        photoRepository.delete(photo);
       
    }
}

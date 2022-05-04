package com.enterarte.services;

import com.enterarte.entities.Contact;
import com.enterarte.mistakes.ErrorService;
import com.enterarte.repositories.ContactRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    
    public final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    ////////////////  Crear mensaje  ////////////////
    
    public void sendContact(Contact contact) throws Exception {
       validar(contact);
       contact.setAlta(true);
       contactRepository.save(contact);
       
    }

    ////////////////  Listar Alta/Baja  ////////////////
    
     public List<Contact> listarMenssageActivos() {
        contactRepository.ContactActivos(Boolean.TRUE); 
        return  contactRepository.ContactActivos(Boolean.TRUE);
    }
     
    public List<Contact> listarMenssageBajas() {
        contactRepository.ContactActivos(Boolean.FALSE);     
        return  contactRepository.ContactActivos(Boolean.FALSE);
    }
    
    ////////////////  Archivar/Alta/Eliminar  ////////////////
   
        public void archivar(Contact contact) throws Exception {
       contact.setAlta(false);
       contactRepository.save(contact);
       
    }
     public void darDeAlta(Contact contact) throws Exception {
       contact.setAlta(true);
       contactRepository.save(contact);
       
    }   
     @Transactional
     public void eliminar(Contact contact) throws Exception {    
       
       contactRepository.deleteAll();
       
    } 
     
     ////////////////  Buscar por id  ////////////////
     
     @Transactional
    public Contact findById(String id) throws Exception {
        Optional<Contact> option = contactRepository.findById(id);
        if (option.isPresent()) {
            Contact contact = option.get();
            return contact;
        } else {
            throw new Exception("usuario no encontrado");
        }
    }
    
    private void validar(Contact contact) throws ErrorService {
        
        if (contact.getNombre().isEmpty()) {
            throw new ErrorService("Tiene que ingresar un nombre");
        }
        if (contact.getMail().isEmpty()) {
            throw new ErrorService("Tiene que ingresar un mail");
        }
        if (contact.getTelefono().isEmpty()) {
            throw new ErrorService("Tiene que ingresar un telefono");
        }
        if (contact.getCuerpo().isEmpty()) {
            throw new ErrorService("Tiene que ingresar un mensaje");
        }
    }
}

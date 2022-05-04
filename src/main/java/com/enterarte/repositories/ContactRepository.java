 
package com.enterarte.repositories;

import com.enterarte.entities.Contact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    
//@Query("SELECT c FROM Contact c WHERE c.contact.id = :id AND c.alta = 1")
//public List<Contact>FindbyContactAlta(@Param("id") String id);
//
//@Query("SELECT c FROM Contact c WHERE c.contact.id = :id AND c.alta = 0")
//public List<Contact>FindbyContactBaja(@Param("id") String id);

@Query("SELECT c FROM Contact c WHERE c.alta = :alta")
    public List<Contact> ContactActivos(@Param("alta")Boolean alta);
    
}

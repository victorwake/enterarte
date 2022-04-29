package com.enterarte.repositories;

import com.enterarte.entities.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    
    //    @Query("SELECT c FROM Customer c WHERE c.nombre LIKE %:nombre%")
//    public List<Customer> findByName(@Param("nombre") String nombre);
//    
// public List<Customer> fondByActiveTrue();
//    
//    @Query("SELECT c FROM Customer c WHERE c.nombre LIKE %?1%"
//            + "OR c.apellido LIKE %?1%"
//            + "OR c.dni LIKE %?1%")
//    public List<Customer> find(String clave);
    
    
    public Optional<Customer> findByEmail(String email);

    
    
    public Optional<Customer> findByDni(String dni);
    
}

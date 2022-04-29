package com.enterarte.repositories;

import com.enterarte.entities.Play;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Play, String>{
    
    
    
}

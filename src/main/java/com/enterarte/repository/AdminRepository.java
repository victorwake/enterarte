package com.enterarte.repository;

import com.enterarte.entity.Play;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Play, String>{
    
    
    
}

package com.enterarte.repositories;

import com.enterarte.entities.Photo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
    
    public Optional<Photo> findById(String email);
    
}

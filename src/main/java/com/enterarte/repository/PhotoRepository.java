package com.enterarte.repository;

import com.enterarte.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
    
}

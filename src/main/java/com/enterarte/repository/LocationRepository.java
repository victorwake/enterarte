package com.enterarte.repository;

import com.enterarte.entity.Location;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface LocationRepository extends JpaRepository<Location, String>{
    
 @Query("SELECT l FROM Location l WHERE l.alta = :alta")
    public List<Location> locationActivos(@Param("alta")Boolean alta);

    
    
}

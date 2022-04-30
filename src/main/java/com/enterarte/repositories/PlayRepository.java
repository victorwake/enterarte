package com.enterarte.repositories;

import com.enterarte.entities.Location;
import com.enterarte.entities.Play;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends JpaRepository<Play, String> {

@Query("SELECT a FROM Play a WHERE a.nombre = :nombre")
public Optional<Play> findByName(@Param("nombre") String nombre);

@Query("SELECT a FROM Play a WHERE a.alta = :alta")
public List<Play> playActivos(@Param("alta")Boolean alta);

}

package com.enterarte.repository;

import com.enterarte.entity.Play;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends JpaRepository<Play, String> {

@Query("SELECT a FROM Play a WHERE a.nombre = :nombre")
public Optional<Play> findByName(String nombre);

}

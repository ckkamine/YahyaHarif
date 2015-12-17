package com.lambda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;

public interface EncadrantRepository extends JpaRepository<Encadrant, Long> {
	
	@Query("select e.collaborateursCurrent from Encadrant e where e.matricule=:m")
	List<Collaborateur> findColloborateurCurrent(@Param("m") Long matricule);

}

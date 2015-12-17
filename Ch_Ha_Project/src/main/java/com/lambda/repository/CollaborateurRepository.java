package com.lambda.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.Collaborateur;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long>{
	
	@Query("select c from Collaborateur c where c.bap.status = :status ")
	List<Collaborateur> getCollaborateursCurrent(@Param("status") String status);
	

}

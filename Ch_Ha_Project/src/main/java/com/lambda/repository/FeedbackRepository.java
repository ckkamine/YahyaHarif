package com.lambda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
	
	@Query("select f from Feedback f where f.encadrant.matricule=:matricule and archive= false")
	Page<Feedback> getFeedbacks(@Param("matricule") Long matricule, Pageable page);
	
	@Query("select f from Feedback f where f.encadrant.matricule=:matricule and archive= false")
	List<Feedback> getFeedbacksList(@Param("matricule") Long matricule);
	
	@Query("select f from Feedback f where f.collaborateur.matricule=:matricule and archive= true and locked=false")
	Page<Feedback> getFeedbacksArchiveCollaborateur(@Param("matricule") Long matricule, Pageable page);
	
	@Query("select f from Feedback f where f.encadrant.matricule=:matricule and archive= true")
	Page<Feedback> getFeedbacksA(@Param("matricule") Long matricule, Pageable page);
	
}

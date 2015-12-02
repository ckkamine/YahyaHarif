package com.lambda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
	
	@Query("select f from Feedback f where f.encadrant.matricule=:matricule")
	Page<Feedback> getFeedbacks(@Param("matricule") Long matricule, Pageable page);
	
	@Query("select f from Feedback f where f.commentaire=:x")
	List<Feedback> getFeedbacks2(@Param("x") String matricule);
}

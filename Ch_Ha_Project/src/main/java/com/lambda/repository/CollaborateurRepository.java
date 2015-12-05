package com.lambda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.Collaborateur;
import com.lambda.entities.Objectif;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long>{
	
//	@Query("select c.objectifs from Collaborateur c left join c.objectifs as o where c.matricule= :x and o.archive= false")
//	Page<Objectif> getObjectifsC(@Param("x") Long matricule, Pageable page);
//	
//	@Query("select c.objectifs from Collaborateur c left join c.objectifs as o where c.matricule= :x and o.archive= true")
//	Page<Objectif> getObjectifsA(@Param("x") Long matricule, Pageable page);

}

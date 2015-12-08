package com.lambda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long>{
	
	@Query("select p from Projet p where p.nom like :x or p.chefProjet.firstName like :x or p.chefProjet.lastName like :x")
	Page<Projet> findByMcProjet(@Param("x") String mc, Pageable page);
	
	@Query("select p from Projet p where p.idProjet like :x")
	Page<Projet> findByIdProjet(@Param("x") Long mc, Pageable page);

	@Query("select p from Projet p where p.chefProjet.matricule = :x")
	List<Projet> getProjetsByChef(@Param("x") Long matricule);
}

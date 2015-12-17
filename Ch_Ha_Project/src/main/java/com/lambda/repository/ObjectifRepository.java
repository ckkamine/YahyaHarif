package com.lambda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.Objectif;

public interface ObjectifRepository extends JpaRepository<Objectif, Long>{

	@Query("select o from Objectif o left join o.descriptions as i where o.objectif like :x or o.categorie like :x or i.description like :x group by o")
	Page<Objectif> findByMcObjectif(@Param("x") String mc, Pageable page);
	
	@Query("select o.idObjectif, o.categorie, o.objectif from Objectif o  where o.employe.matricule = :x and o.archive= false")
	Page<Objectif> getObjectifsC(@Param("x") Long matricule, Pageable page);
	
	@Query("select o.idObjectif, o.categorie, o.objectif from Objectif o  where o.employe.matricule = :x and o.archive= true")
	Page<Objectif> getObjectifsA(@Param("x") Long matricule, Pageable page);
	
	@Query("select o from Objectif o  where o.employe.matricule = :x and o.archive= false")
	List<Objectif> getObjectifsCList(@Param("x") Long matricule);
}

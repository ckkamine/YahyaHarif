package com.lambda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.Objectif;

public interface ObjectifRepository extends JpaRepository<Objectif, Long>{

	@Query("select o from Objectif o left join o.descriptions as i where o.nom like :x or o.type like :x or i.description like :x group by o")
	Page<Objectif> findByMcObjectif(@Param("x") String mc, Pageable page);
}

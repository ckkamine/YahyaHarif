package com.lambda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.BAP;

public interface BapRepository extends JpaRepository<BAP, Long>{

	@Query("select b from BAP b")
	Page<BAP> getAllBap(Pageable page);
	
	@Query("select b from BAP b where b.status like :x or b.decision like :x or b.collaborateur.username like :x"
			+ " or b.collaborateur.firstName like :x or b.collaborateur.lastName like :x")
	Page<BAP> findByMcBap(@Param("x") String mc, Pageable page);
	
	@Query("select b from BAP b where b.collaborateur.matricule = :x")
	BAP findByCollaborateur(@Param("x") Long matricule);

	@Query("select b from BAP b where b.locked= false and b.collaborateur.matricule = :x")
	BAP findByCollaborateurPrivate(@Param("x") Long matricule);
	
	@Query("select b from BAP b where b.manager.matricule = :x")
	Page<BAP> findByManager(@Param("x") Long matricule, Pageable page);
	
}


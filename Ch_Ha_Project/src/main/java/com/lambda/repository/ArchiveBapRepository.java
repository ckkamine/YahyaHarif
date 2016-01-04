package com.lambda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.ArchiveBap;

public interface ArchiveBapRepository extends JpaRepository<ArchiveBap, Long> {

	@Query("select a from ArchiveBap a")
	Page<ArchiveBap> getAllArchiveBap(Pageable page);

	@Query("select a from ArchiveBap a where a.locked= false and a.collaborateur.matricule = :x")
	Page<ArchiveBap> findByCollaborateur(@Param("x") Long matricule, Pageable page);

	@Query("select a from ArchiveBap a where a.manager.matricule = :x")
	Page<ArchiveBap> findByManager(@Param("x") Long matricule, Pageable page);

}

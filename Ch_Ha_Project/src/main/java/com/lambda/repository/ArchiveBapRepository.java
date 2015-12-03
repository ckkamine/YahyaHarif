package com.lambda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lambda.entities.ArchiveBap;

public interface ArchiveBapRepository extends JpaRepository<ArchiveBap, Long>{
	
	@Query("select a from ArchiveBap a")
	Page<ArchiveBap> getAllArchiveBap(Pageable page);
	
	@Query("select a from ArchiveBap a where a.decision like :x or a.collaborateur.username like :x"
			+ " or a.collaborateur.firstName like :x or a.collaborateur.lastName like :x")
	Page<ArchiveBap> findByMcArchiveBap(String mc, Pageable page);

}

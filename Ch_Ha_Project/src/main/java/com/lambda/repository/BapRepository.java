package com.lambda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.BAP;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Feedback;

public interface BapRepository extends JpaRepository<BAP, Long>{
	
	@Query("select b from BAP b where b.status like :x or b.decision like :x or b.collaborateur.username like :x"
			+ " or b.collaborateur.firstName like :x or b.collaborateur.lastName like :x")
	Page<BAP> findByMcBap(@Param("x") String mc, Pageable page);
	
	@Query("select b from BAP b where b.collaborateur.matricule = :x")
	BAP findByCollaborateur(@Param("x") Long matricule);

	@Query("select b from BAP b where b.locked= false and b.collaborateur.matricule = :x")
	BAP findByCollaborateurPublic(@Param("x") Long matricule);
	
	@Query("select b from BAP b where b.manager.matricule = :x")
	Page<BAP> findByManager(@Param("x") Long matricule, Pageable page);
	
	@Query("select b.collaborateur from BAP b where b.status = 'En cours'")
	List<Collaborateur> collaborateurCurrent();
	
	
	
	@Query("select b.status from BAP b where b.collaborateur.matricule = :x")
	String findByCollaborateurStatus(@Param("x") Long matricule);
	
	@Query("select b.compteur from BAP b where b.collaborateur.matricule = :x")
	Integer findByCollaborateurCompteur(@Param("x") Long matricule);
	
	@Query("select count(b) from BAP b where b.manager.matricule = :x and status='En cours'")
	Integer nombreEnCours(@Param("x") Long matricule);
	
	@Query("select count(b) from BAP b where b.manager.matricule = :x and status='En attente'")
	Integer nombreEnAttente(@Param("x") Long matricule);
	
	@Query("select count(b) from BAP b where b.manager.matricule = :x and status='Rejeté'")
	Integer nombreRejete(@Param("x") Long matricule);
}


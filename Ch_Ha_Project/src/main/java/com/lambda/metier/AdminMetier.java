package com.lambda.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Bilan;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Manager;
import com.lambda.entities.Objectif;
import com.lambda.entities.Projet;
import com.lambda.entities.User;

public interface AdminMetier{

	Page<User> getAllUsers(int page);
	Page<BAP> getAllBap(int page);
	Page<ArchiveBap> getAllArchiveBap(int page);
	Page<Projet> getAllProjets(int page);
	Page<Objectif> getAllObjectifs(int page);
	BAP addObjectif(Objectif objectif, Long idBap);
	Objectif getObjectif(Long id);
	Objectif updateObjectif(Objectif objectif);
	void deleteObjectif(Long id);
	Projet addProjet(Projet projet);
	Projet getProjet(Long id);
	Projet updateProjet(Projet projet);
	void deleteProjet(Long id);
	Bilan addBilan(Bilan bilan);
	BAP getBilan(Long id);
	BAP updateBilan(BAP bilan);
	void deleteBilan(Long id);
	List<String> getAllUsername();
	User addUser(User user, Long matricule);
	User getUser(Long id);
	User updateUser(User user);
	void deleteUser(Long id);
	ArchiveBap addArchiveBap(ArchiveBap archiveBap);
	ArchiveBap getArchiveBap(Long id);
	ArchiveBap updateArchiveBap(ArchiveBap archiveBap);
	void deleteArchiveBap(Long id);
	Page<User> findByMcUser(String mc, int page);
	Page<BAP> findByMcBap(String mc,int page);
	Page<ArchiveBap> findByMcArchiveBap(String mc,int page);
	Page<Projet> findByMcProjet(String mc,int page);
	Page<Objectif> findByMcObjectif(String mc,int page);
	List<Encadrant> getAllEncadrant();
	List<Collaborateur> getAllCollaborateur();
	List<Manager> getAllManagers();
	BAP addBap(BAP bap);
	BAP envoyerObjectifs(Long id);
	BAP openOrLockBap(Long id);
	
}

package com.lambda.metier;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Bilan;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Objectif;
import com.lambda.entities.Projet;
import com.lambda.entities.User;
import com.lambda.repository.ArchiveBapRepository;
import com.lambda.repository.BapRepository;
import com.lambda.repository.BilanRepository;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.EncadrantRepository;
import com.lambda.repository.ObjectifRepository;
import com.lambda.repository.ProjetRepository;
import com.lambda.repository.UserRepository;

@Service
@Transactional
public class AdminMetierImpl implements AdminMetier{
	@Autowired
	BapRepository bapRepository;
	
	@Autowired
	ArchiveBapRepository archiveBapRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ObjectifRepository objectifRepository;
	
	@Autowired
	ProjetRepository projetRepository;
	
	@Autowired
	BilanRepository bilanRepository;
	
	@Autowired
	CollaborateurRepository collaborateurRepository;
	
	@Autowired
	EncadrantRepository encadrantRepository;

	public Page<User> getAllUsers(int page) {	
		return userRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Page<BAP> getAllBap(int page) {
		// TODO Auto-generated method stub
		return bapRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Page<ArchiveBap> getAllArchiveBap(int page) {
		// TODO Auto-generated method stub
		return archiveBapRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Page<Projet> getAllProjets(int page) {
		// TODO Auto-generated method stub
		return projetRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Page<Objectif> getAllObjectifs(int page) {
		// TODO Auto-generated method stub
		return objectifRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Objectif getObjectif(Long id) {
		
		return objectifRepository.findOne(id);
	}

	@Override
	public Objectif updateObjectif(Objectif objectif) {
		// TODO Auto-generated method stub
		return objectifRepository.save(objectif);
	}

	@Override
	public void deleteObjectif(Long id) {
		objectifRepository.delete(id);
		
	}

	@Override
	public Projet addProjet(Projet projet) {
		return projetRepository.save(projet);
	}

	@Override
	public Projet getProjet(Long id) {
		// TODO Auto-generated method stub
		return projetRepository.findOne(id);
	}

	@Override
	public Projet updateProjet(Projet projet) {
		// TODO Auto-generated method stub
		return projetRepository.save(projet);
	}

	@Override
	public void deleteProjet(Long id) {
		projetRepository.delete(id);
		
	}

	@Override
	public Bilan getBilan(Long id) {
		return bilanRepository.findOne(id);
	}

	@Override
	public Bilan updateBilan(Bilan bilan) {
		// TODO Auto-generated method stub
		return bilanRepository.save(bilan);
	}

	@Override
	public void deleteBilan(Long id) {
		bilanRepository.delete(id);
		
	}

	@Override
	public List<String> getAllUsername() {
		return userRepository.getAllUsername();
	}

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
		
	}

	@Override
	public ArchiveBap addArchiveBap(ArchiveBap archiveBap) {
		return archiveBapRepository.save(archiveBap);
	}

	@Override
	public ArchiveBap getArchiveBap(Long id) {
		// TODO Auto-generated method stub
		return archiveBapRepository.findOne(id);
	}

	@Override
	public ArchiveBap updateArchiveBap(ArchiveBap archiveBap) {
		// TODO Auto-generated method stub
		return archiveBapRepository.save(archiveBap);
	}

	@Override
	public void deleteArchiveBap(Long id) {
		archiveBapRepository.delete(id);
		
	}

	@Override
	public Page<User> findByMcUser(String mc, int page) {
		// TODO Auto-generated method stub
		return userRepository.findByMcUsers("%"+mc+"%", new PageRequest(page, 10));
	}

	@Override
	public Page<BAP> findByMcBap(String mc, int page) {
		// TODO Auto-generated method stub
		return bapRepository.findByMcBap("%"+mc+"%", new PageRequest(page, 10));
	}

	@Override
	public Page<ArchiveBap> findByMcArchiveBap(String mc, int page) {
		// TODO Auto-generated method stub
		return archiveBapRepository.findByMcArchiveBap("%"+mc+"%", new PageRequest(page, 10));
	}

	@Override
	public Page<Projet> findByMcProjet(String mc, int page) {
		return projetRepository.findByMcProjet("%"+mc+"%", new PageRequest(page, 10));
	}

	@Override
	public Page<Objectif> findByMcObjectif(String mc, int page) {
		// TODO Auto-generated method stub
		return objectifRepository.findByMcObjectif("%"+mc+"%", new PageRequest(page, 10));
	}

	@Override
	public List<Encadrant> getAllEncadrant() {
		// TODO Auto-generated method stub
		return encadrantRepository.findAll();
	}

	@Override
	public List<Collaborateur> getAllCollaborateur() {
		// TODO Auto-generated method stub
		return collaborateurRepository.findAll();
	}

	

}

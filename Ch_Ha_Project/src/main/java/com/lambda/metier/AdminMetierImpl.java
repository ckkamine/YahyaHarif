package com.lambda.metier;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Bilan;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Manager;
import com.lambda.entities.Objectif;
import com.lambda.entities.Projet;
import com.lambda.entities.User;
import com.lambda.mail.MailComponent;
import com.lambda.repository.ArchiveBapRepository;
import com.lambda.repository.BapRepository;
import com.lambda.repository.BilanRepository;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.EncadrantRepository;
import com.lambda.repository.ManagerRepository;
import com.lambda.repository.ObjectifRepository;
import com.lambda.repository.ProjetRepository;
import com.lambda.repository.UserRepository;

@Service
@Transactional
public class AdminMetierImpl implements AdminMetier{
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MailComponent mailService;
	
	@Autowired
	BapRepository bapRepository;
	
	@Autowired
	ArchiveBapRepository archiveBapRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
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
		Objectif o= objectifRepository.findOne(id);
		o.setArchive(true);
	}

	@Override
	public Projet addProjet(Projet projet) {
		Encadrant e= encadrantRepository.findOne(projet.getChefProjet().getMatricule());
		projet.setChefProjet(e);
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
	public User addUser(User user, Long matricule) {
		String generatedPassword = RandomStringUtils.randomAlphabetic(8);
		user.setPassword(passwordEncoder.encode(generatedPassword));
		try {
			mailService.sendUserpass(user.getEmail(), user.getFirstName(), user.getLastName(), user.getUsername(), generatedPassword);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println(generatedPassword);
		if(user instanceof Collaborateur){
			Manager m= managerRepository.findOne(matricule);
			BAP bap = new BAP(new Date(user.getDateRecrutement().getYear()+1, user.getDateRecrutement().getMonth(), 1),
					(Collaborateur) user, true, m);
			userRepository.save(user);
			bapRepository.save(bap);
			return user;
		}
		return userRepository.save(user);
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User updateUser(User user) {
		User u= userRepository.findOne(user.getMatricule());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		u.setAdresse(user.getAdresse());
		u.setPosteActuel(user.getPosteActuel());
		u.setTelephone(user.getTelephone());
		return u;
	}

	@Override
	public void deleteUser(Long id) {
		
	
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
		try {
			Long id= Long.parseLong(mc, 10);
			return userRepository.findByMcUsersId(id, new PageRequest(page, 10));
		} catch (NumberFormatException e) {
			return userRepository.findByMcUsers("%"+mc+"%", new PageRequest(page, 10));
		}
		
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
		try {
			Long id= Long.parseLong(mc, 10);
			return projetRepository.findByIdProjet(id, new PageRequest(page, 10));
		} catch (NumberFormatException e) {
			System.out.println(mc);
			return projetRepository.findByMcProjet("%"+mc+"%", new PageRequest(page, 10));
		}
		
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

	@Override
	public List<Manager> getAllManagers() {
		// TODO Auto-generated method stub
		return managerRepository.findAll();
	}

	

}

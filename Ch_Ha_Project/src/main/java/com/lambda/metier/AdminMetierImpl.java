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
import com.lambda.entities.Feedback;
import com.lambda.entities.Manager;
import com.lambda.entities.Objectif;
import com.lambda.entities.Projet;
import com.lambda.entities.User;
import com.lambda.mail.MailComponent;
import com.lambda.repository.ArchiveBapRepository;
import com.lambda.repository.BapRepository;
import com.lambda.repository.BilanRepository;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.DescriptionRepository;
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
	
	@Autowired
	DescriptionRepository descriptionRepository;

	public Page<User> getAllUsers(int page) {	
		return userRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Page<BAP> getAllBap(int page) {
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
	public BAP getBilan(Long id) {
		return bapRepository.findOne(id);
	}

	@Override
	public BAP updateBilan(BAP bilan) {
		return bapRepository.save(bilan);
		
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
					(Collaborateur) user, m);
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
		return bapRepository.findByMcBap("%"+mc+"%", new PageRequest(page, 10));
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
		return managerRepository.findAll();
	}

	@Override
	public BAP addObjectif(Objectif objectif, Long idBap) {
		objectifRepository.save(objectif);
		BAP bap= bapRepository.findOne(idBap);
		bap.addObjectifSortantes(objectif);
	
		return bap;
	}

	
	@Override
	public BAP addBap(BAP bap) {
		return bapRepository.save(bap);
	}

	@Override
	public BAP envoyerObjectifs(Long id) {
		BAP bap= bapRepository.findOne(id);
		bap.setCompteur(bap.getCompteur()+1);
		bap.setStatus(bap.EN_COURS);
		if(bap.getCompteur()>=3){
			for(Objectif f: bap.getObjectifsSortantes()){
				f.setValide(true);
			}
		}
		return bap;
	}

	@Override
	public BAP openOrLockBap(Long id) {
		BAP bap= bapRepository.findOne(id);
		if(bap.isLocked()){
			bap.setLocked(false);
			for(Feedback f: bap.getFeedbacks()){
				f.setLocked(false);
			}
		}else{
			bap.setLocked(true);
			for(Feedback f: bap.getFeedbacks()){
				f.setLocked(true);
			}
		}
		bap= bapRepository.findOne(id);
		return bap;
	}

	@Override
	public void validerBap(Long idBap) {
		BAP b= bapRepository.findOne(idBap);
		for(Objectif o: b.getObjectifsEntrantes()){
			o.setArchive(true);
		}
		for(Feedback f: b.getFeedbacks()){
			f.setArchive(true);
		}
		ArchiveBap archive= new ArchiveBap(b.getId(), b.getDateBilan(), b.getCollaborateur(), b.getObjectifsEntrantes(), b.getDecision(), b.getFeedbacks(), b.isLocked(), b.getObjectifsSortantes(), b.getManager(), b.getNoteGlobale());
		archiveBapRepository.save(archive);
		Date date= new Date(b.getDateBilan().getYear()+1, b.getDateBilan().getMonth(), b.getDateBilan().getDate());
		BAP newBap= new BAP(date, b.getCollaborateur(), b.getManager());
		bapRepository.delete(b.getId());
		bapRepository.save(newBap);
		
	}

	@Override
	public void AnnulerBap(Long idBap) {
		BAP bap= bapRepository.findOne(idBap);
		bap.setStatus(bap.ANNULE);
	}

	@Override
	public Integer nombreEnCours(Long matricule) {
		// TODO Auto-generated method stub
		return bapRepository.nombreEnCours(matricule);
	}

	@Override
	public Integer nombreRejete(Long matricule) {
		// TODO Auto-generated method stub
		return bapRepository.nombreRejete(matricule);
	}

	@Override
	public Integer nombreEnAttente(Long matricule) {
		// TODO Auto-generated method stub
		return bapRepository.nombreEnAttente(matricule);
	}
	

}

package com.lambda.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Manager;
import com.lambda.entities.Objectif;
import com.lambda.repository.ArchiveBapRepository;
import com.lambda.repository.BapRepository;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.ManagerRepository;
import com.lambda.repository.ObjectifRepository;

@Service
@Transactional
public class ManagerMetierImpl implements ManagerMetier {
	
	@Autowired
	ObjectifRepository objectifRepository;
	
	@Autowired
	ArchiveBapRepository archiveBapRepository;
	
	@Autowired
	BapRepository bapRepository;

	@Autowired
	CollaborateurRepository collaborateurRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
	@Override
	public Page<BAP> getAllBapManager(Long matricule, int page) {
		// TODO Auto-generated method stub
		return bapRepository.findByManager(matricule, new PageRequest(page, 10));
	}

	@Override
	public Page<ArchiveBap> getAllArchiveBapManager(Long matricule, int page) {
		// TODO Auto-generated method stub
		return archiveBapRepository.findByManager(matricule, new PageRequest(page, 10));
	}

	@Override
	public BAP getBap(Long idBap) {
		// TODO Auto-generated method stub
		return bapRepository.findOne(idBap);
	}

	@Override
	public List<Collaborateur> getAllCollaborateur() {
		// TODO Auto-generated method stub
		return collaborateurRepository.findAll();
	}

	@Override
	public void fermerBap(Long idBap) {
		BAP b= bapRepository.findOne(idBap);
		b.setLocked(true);

	}

	@Override
	public void ouvrirBap(Long idBap) {
		BAP b= bapRepository.findOne(idBap);
		b.setLocked(false);

	}

	@Override
	public Objectif addObjectif(Objectif objectif, Long idBap) {
		objectifRepository.save(objectif);
		BAP b= bapRepository.findOne(idBap);
		b.getObjectifsEntrantes().add(objectif);
		return objectif;
	}

	

	@Override
	public void validerBap(Long idBap) {
		BAP b= bapRepository.findOne(idBap);
		for(Objectif o: b.getObjectifsSortantes()){
			o.setEmploye(b.getCollaborateur());
		}
		ArchiveBap a= new ArchiveBap(b.getId(), b.getDateBilan(), b.getCollaborateur(), b.getObjectifsEntrantes(), b.getDecision(), b.getFeedbacks(), b.isLocked(), b.getObjectifsSortantes(), b.getManager());
		archiveBapRepository.save(a);
		BAP newB= new BAP( b.addYear(b.getDateBilan()), b.getCollaborateur(), true, b.EN_ATTENTE, b.getManager(), 0);
		bapRepository.save(newB);
		bapRepository.delete(b);
		
	}

	@Override
	public BAP preparerBap(Long idBap) {
		BAP b= bapRepository.findOne(idBap);
		b.setStatus(b.EN_COURS);
		return b;
	}

	

}

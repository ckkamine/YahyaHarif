package com.lambda.metier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Feedback;
import com.lambda.entities.Objectif;
import com.lambda.repository.ArchiveBapRepository;
import com.lambda.repository.BapRepository;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.EncadrantRepository;
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

	@Autowired
	EncadrantRepository encadrantRepository;

	@Override
	public Page<BAP> getAllBapManager(Long matricule, int page) {
		return bapRepository.findByManager(matricule, new PageRequest(page, 10));
	}

	@Override
	public Page<ArchiveBap> getAllArchiveBapManager(Long matricule, int page) {
		return archiveBapRepository.findByManager(matricule, new PageRequest(page, 10));
	}

	@Override
	public List<Collaborateur> getAllCollaborateur() {
		return collaborateurRepository.findAll();
	}

	@Override
	public BAP addObjectif(Objectif objectif, Long idBap) {
		objectifRepository.save(objectif);
		BAP bap = bapRepository.findOne(idBap);
		bap.addObjectifSortantes(objectif);
		return bap;
	}

	@Override
	public void validerBap(Long idBap) {
		BAP b = bapRepository.findOne(idBap);
		for (Objectif o : b.getObjectifsEntrantes()) {
			o.setArchive(true);
		}
		for (Feedback f : b.getFeedbacks()) {
			f.setArchive(true);
		}
		for (Objectif o : b.getObjectifsSortantes()) {
			System.out.println(o.getIdObjectif());
		}
		ArchiveBap archive = new ArchiveBap(b.getId(), b.getDateBilan(), b.getCollaborateur(),
				b.getObjectifsEntrantes(), b.getDecision(), b.getFeedbacks(), b.isLocked(), b.getObjectifsSortantes(),
				b.getManager(), b.getNoteGlobale());
		archiveBapRepository.save(archive);
		@SuppressWarnings("deprecation")
		Date date = new Date(b.getDateBilan().getYear() + 1, b.getDateBilan().getMonth(), b.getDateBilan().getDate());
		BAP newBap = new BAP(date, b.getCollaborateur(), b.getManager());
		bapRepository.delete(b.getId());
		bapRepository.save(newBap);

	}

	@Override
	public List<Encadrant> getAllEncadrant() {

		return encadrantRepository.findAll();
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
	public void deleteObjectif(Long id) {
		objectifRepository.delete(id);

	}

	@SuppressWarnings("static-access")
	@Override
	public BAP envoyerObjectifs(Long id) {
		BAP bap = bapRepository.findOne(id);
		bap.setEnvoye(true);
		bap.setCompteur(bap.getCompteur() + 1);
		bap.setStatus(bap.EN_COURS);
		if (bap.getCompteur() >= 3) {
			for (Objectif f : bap.getObjectifsSortantes()) {
				f.setValide(true);
			}

		}
		return bap;
	}

	@Override
	public BAP openOrLockBap(Long id) {
		BAP bap = bapRepository.findOne(id);
		if (bap.isLocked()) {
			bap.setLocked(false);
			for (Feedback f : bap.getFeedbacks()) {
				f.setLocked(false);
			}
		} else {
			bap.setLocked(true);
			for (Feedback f : bap.getFeedbacks()) {
				f.setLocked(true);
			}
		}
		return bap;
	}

	@SuppressWarnings("static-access")
	@Override
	public void AnnulerBap(Long idBap) {
		BAP bap = bapRepository.findOne(idBap);
		bap.setStatus(bap.ANNULE);
	}

	@Override
	public Integer nombreEnCours(Long matricule) {
		return bapRepository.nombreEnCours(matricule);
	}

	@Override
	public Integer nombreRejete(Long matricule) {
		return bapRepository.nombreRejete(matricule);
	}

	@Override
	public Integer nombreEnAttente(Long matricule) {
		return bapRepository.nombreEnAttente(matricule);
	}

	@Override
	public Page<ArchiveBap> getAllArchiveBap(Long matricule, int page) {
		return archiveBapRepository.findByManager(matricule, new PageRequest(page, 10));
	}

}

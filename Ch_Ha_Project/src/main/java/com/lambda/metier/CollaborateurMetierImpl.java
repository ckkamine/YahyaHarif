package com.lambda.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Feedback;
import com.lambda.entities.Objectif;
import com.lambda.repository.ArchiveBapRepository;
import com.lambda.repository.BapRepository;
import com.lambda.repository.FeedbackRepository;
import com.lambda.repository.ObjectifRepository;

@Service
@Transactional
public class CollaborateurMetierImpl implements CollaborateurMetier {

	@Autowired
	BapRepository bapRepository;

	@Autowired
	ArchiveBapRepository archiveBapRepository;

	@Autowired
	ObjectifRepository objectifRepository;

	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	public BAP getBapCourrant(Long matricule) {
		return bapRepository.findByCollaborateurPublic(matricule);
	}

	@Override
	public Page<ArchiveBap> getAllBapArchive(Long matricule, int page) {
		return archiveBapRepository.findByCollaborateur(matricule, new PageRequest(page, 10));
	}

	@SuppressWarnings("static-access")
	@Override
	public void envoyerObjectifs(Long matricule) {
		BAP b = bapRepository.findByCollaborateur(matricule);
		if (b.getCompteur() < 3) {
			b.setEnvoye(false);
		}
		for (Objectif f : b.getObjectifsEntrantes()) {
			if (!f.isValide()) {
				b.setStatus(b.REJETE);
			}
		}
	}

	@Override
	public Page<Objectif> getObjectifCourrant(Long matricule, int page) {
		return objectifRepository.getObjectifsC(matricule, new PageRequest(page, 5));
	}

	@Override
	public Page<Objectif> getObjetifArchive(Long matricule, int page) {
		return objectifRepository.getObjectifsA(matricule, new PageRequest(page, 5));
	}

	@Override
	public List<Objectif> getNewObjectif(Long matricule) {
		BAP bap = bapRepository.findByCollaborateur(matricule);
		if (bap.isEnvoye()) {
			return (List<Objectif>) bap.getObjectifsSortantes();
		}
		return null;
	}

	@Override
	public Page<Feedback> getAllFeedbacksArchives(Long matricule, int page) {
		return feedbackRepository.getFeedbacksArchiveCollaborateur(matricule, new PageRequest(page, 10));
	}

	@Override
	public String getBapStatus(Long matricule) {
		return bapRepository.findByCollaborateurStatus(matricule);
	}

	@Override
	public void validerOrRefuserObjectif(Long idObjectif) {
		Objectif o = objectifRepository.findOne(idObjectif);
		if (o.isValide()) {
			o.setValide(false);
		} else {
			o.setValide(true);
		}

	}

	@Override
	public Integer getNombreDeRefus(Long matricule) {
		return bapRepository.findByCollaborateurCompteur(matricule);
	}

}

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
import com.lambda.entities.Objectif;
import com.lambda.repository.ArchiveBapRepository;
import com.lambda.repository.BapRepository;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.ObjectifRepository;


@Service
@Transactional
public class CollaborateurMetierImpl implements CollaborateurMetier{

	@Autowired
	BapRepository bapRepository;
	
	@Autowired
	ArchiveBapRepository archiveBapRepository;
	
	@Autowired
	ObjectifRepository objectifRepository;
	
	@Override
	public BAP getBapCourrant(Long matricule) {
		// TODO Auto-generated method stub
		return bapRepository.findByCollaborateurPublic(matricule);
	}

	@Override
	public Page<ArchiveBap> getAllBapArchive(Long matricule, int page) {
		// TODO Auto-generated method stub
		return archiveBapRepository.findByCollaborateur(matricule, new PageRequest(page, 10));
	}


	@Override
	public void validerObjectif(Long idObjectif) {
		Objectif o= objectifRepository.findOne(idObjectif);
		o.setValide(true);
	}

	@Override
	public void refuserObjectif(Long idObjectif) {
		Objectif o= objectifRepository.findOne(idObjectif);
		o.setValide(false);
	}

	@Override
	public void envoyerObjectifs(Long matricule) {
		BAP b= bapRepository.findByCollaborateur(matricule);
		if(b.getCompteur() < 3){
			b.setStatus(b.VALIDE);
			for(Objectif o: b.getObjectifsSortantes()){
				if(!o.isValide()){
					b.setStatus(b.REJETE);
					break;
				}
			}
			
		}
		b.setCompteur(b.getCompteur()+1);
	}

	@Override
	public Page<Objectif> getObjectifCourrant(Long matricule, int page) {
		return objectifRepository.getObjectifsC(matricule, new PageRequest(page, 10));
	}

	@Override
	public Page<Objectif> getObjetifArchive(Long matricule, int page) {
		return objectifRepository.getObjectifsA(matricule, new PageRequest(page, 10));
	}

	@Override
	public List<Objectif> getNewObjectif(Long matricule) {
		BAP bap= bapRepository.findByCollaborateur(matricule);
		if(bap.isEnvoye()){
			return (List<Objectif>) bap.getObjectifsSortantes();
		}
		return null;
	}

}

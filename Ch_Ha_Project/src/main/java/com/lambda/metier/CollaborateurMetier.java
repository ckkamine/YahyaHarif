package com.lambda.metier;

import org.springframework.data.domain.Page;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Objectif;

public interface CollaborateurMetier {
	
	public BAP getBapCourrant(Long matricule);
	public Page<ArchiveBap> getAllBapArchive(Long matricule, int page);
	public void envoyerObjectifs(Long matricule);
	public void validerObjectif(Long idObjectif);
	public void refuserObjectif(Long idObjectif);
	public Page<Objectif> getObjectifCourrant(Long matricule, int page);
	public Page<Objectif> getObjetifArchive(Long matricule, int page);

}

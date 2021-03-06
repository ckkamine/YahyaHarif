package com.lambda.metier;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Feedback;
import com.lambda.entities.Objectif;

public interface CollaborateurMetier {
	
	public BAP getBapCourrant(Long matricule);
	public Page<ArchiveBap> getAllBapArchive(Long matricule, int page);
	public Page<Feedback> getAllFeedbacksArchives(Long matricule, int page);
	public void envoyerObjectifs(Long matricule);
	public void validerOrRefuserObjectif(Long idObjectif);
	public Page<Objectif> getObjectifCourrant(Long matricule, int page);
	public Page<Objectif> getObjetifArchive(Long matricule, int page);
	public List<Objectif> getNewObjectif(Long matricule);
	public String getBapStatus(Long matricule);
	public Integer getNombreDeRefus(Long matricule);
	
}

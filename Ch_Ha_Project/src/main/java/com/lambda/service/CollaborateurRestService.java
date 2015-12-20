package com.lambda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Feedback;
import com.lambda.entities.Objectif;
import com.lambda.metier.CollaborateurMetier;

@RestController
@RequestMapping("/rest/collaborateur")
public class CollaborateurRestService {

	@Autowired
	CollaborateurMetier collaborateurMetier;

	@RequestMapping(value="/bapc", method= RequestMethod.GET)
	public BAP getBapCourrant(@RequestParam Long matricule) {
		return collaborateurMetier.getBapCourrant(matricule);
	}

	@RequestMapping(value="/bapa", method= RequestMethod.GET)
	public Page<ArchiveBap> getAllBapArchive(@RequestParam Long matricule, @RequestParam int page) {
		return collaborateurMetier.getAllBapArchive(matricule, page);
	}

	@RequestMapping(value="/feedbacksa", method= RequestMethod.GET)
	public Page<Feedback> getAllFeedbacksArchives(@RequestParam Long matricule, @RequestParam int page) {
		return collaborateurMetier.getAllFeedbacksArchives(matricule, page);
	}

	@RequestMapping(value="/envoyer", method= RequestMethod.POST)
	public void envoyerObjectifs(@RequestParam Long matricule) {
		collaborateurMetier.envoyerObjectifs(matricule);
	}

	
	@RequestMapping(value="/validerorrefuser", method= RequestMethod.POST)
	public void validerOrRefuserObjectif(@RequestParam Long idObjectif) {
		collaborateurMetier.validerOrRefuserObjectif(idObjectif);
	}

	@RequestMapping(value="/objectifsc", method= RequestMethod.GET)
	public Page<Objectif> getObjectifCourrant(@RequestParam Long matricule, @RequestParam int page) {
		return collaborateurMetier.getObjectifCourrant(matricule, page);
	}

	@RequestMapping(value="/objectifsa", method= RequestMethod.GET)
	public Page<Objectif> getObjetifArchive(@RequestParam Long matricule, @RequestParam int page) {
		return collaborateurMetier.getObjetifArchive(matricule, page);
	}

	@RequestMapping(value="/objectifsn", method= RequestMethod.GET)
	public List<Objectif> getNewObjectif(@RequestParam Long matricule) {
		return collaborateurMetier.getNewObjectif(matricule);
	}

	@RequestMapping(value="/status", method= RequestMethod.GET)
	public String getBapStatus(@RequestParam Long matricule) {
		return collaborateurMetier.getBapStatus(matricule);
	}

	@RequestMapping(value="/compteur", method= RequestMethod.GET)
	public Integer getNombreDeRefus(@RequestParam Long matricule) {
		return collaborateurMetier.getNombreDeRefus(matricule);
	}
	
	
	
	
	
}

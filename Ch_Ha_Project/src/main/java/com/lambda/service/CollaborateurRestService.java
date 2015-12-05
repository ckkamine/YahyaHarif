package com.lambda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Objectif;
import com.lambda.metier.CollaborateurMetier;

@RestController
@RequestMapping("/rest/collaborateur")
public class CollaborateurRestService {

	@Autowired
	CollaborateurMetier collaborateurMetier;
	
	
	@RequestMapping(value="/bapc", method= RequestMethod.POST)
	public BAP getBapCourrant(@RequestBody Long matricule) {
		return collaborateurMetier.getBapCourrant(matricule);
	}

	@RequestMapping(value="/bapa", method= RequestMethod.POST)
	public Page<ArchiveBap> getAllBapArchive(@RequestBody Long matricule,@RequestParam int page) {
		return collaborateurMetier.getAllBapArchive(matricule, page);
	}
	
	@RequestMapping(value="/envoyer", method= RequestMethod.POST)
	public void envoyerObjectifs(@RequestBody Long matricule) {
		collaborateurMetier.envoyerObjectifs(matricule);
	}

	@RequestMapping(value="/valider", method= RequestMethod.POST)
	public void validerObjectif(@RequestBody Long idObjectif) {
		collaborateurMetier.validerObjectif(idObjectif);
	}

	@RequestMapping(value="/refuser", method= RequestMethod.POST)
	public void refuserObjectif(@RequestBody Long idObjectif) {
		collaborateurMetier.refuserObjectif(idObjectif);
	}

	@RequestMapping(value="/objectifsc", method= RequestMethod.POST)
	public Page<Objectif> getObjectifCourrant(@RequestBody Long matricule,@RequestParam int page) {
		return collaborateurMetier.getObjectifCourrant(matricule, page);
	}
	
	@RequestMapping(value="/objectifsa", method= RequestMethod.POST)
	public Page<Objectif> getObjetifArchive(@RequestBody Long matricule,@RequestParam int page) {
		return collaborateurMetier.getObjetifArchive(matricule, page);
	}
	
	
}

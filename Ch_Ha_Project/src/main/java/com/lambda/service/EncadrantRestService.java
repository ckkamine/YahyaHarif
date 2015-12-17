package com.lambda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.entities.Collaborateur;
import com.lambda.entities.Feedback;
import com.lambda.entities.Projet;
import com.lambda.entities.Qualification;
import com.lambda.metier.EncadrantMetier;

@RestController
@RequestMapping("/rest/encadrant")
public class EncadrantRestService {

	@Autowired
	EncadrantMetier encadrantMetier;

	@RequestMapping(value="/feedbacks", method= RequestMethod.GET)
	public Page<Feedback> getFeedbacks(@RequestParam(value="matricule") Long matricule, 
			@RequestParam(value = "page") Integer page) {
		return encadrantMetier.getFeedbacks(matricule, page);
	}

	@RequestMapping(value="/feedback", method= RequestMethod.POST)
	public Feedback addFeedback(@RequestBody Feedback feedback) {
		return encadrantMetier.addFeedback(feedback);
	}

	@RequestMapping(value="/projets", method= RequestMethod.GET)
	public List<Projet> getProjets(@RequestParam(value="matricule") Long matricule) {
		return encadrantMetier.getProjets(matricule);
	}

	@RequestMapping(value="/collaborateurs", method= RequestMethod.GET)
	public List<Collaborateur> getCollaborateursCurrent(@RequestParam Long matricule) {
		return encadrantMetier.getCollaborateursCurrent(matricule);
	}

	@RequestMapping(value="/feedback", method= RequestMethod.DELETE)
	public void retirerFeedback(@RequestParam Long idFeedback, @RequestParam Long matricule) {
		encadrantMetier.retirerFeedback(matricule, idFeedback);
	}
	
	
}

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
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Objectif;
import com.lambda.metier.ManagerMetier;

@RestController
@RequestMapping("/rest/manager")
public class ManagerRestService {

	@Autowired
	ManagerMetier managerMetier;

	@RequestMapping(value = "/encadrants", method = RequestMethod.GET)
	public List<Encadrant> getAllEncadrant() {
		return managerMetier.getAllEncadrant();
	}

	@RequestMapping(value = "/bilan", method = RequestMethod.GET)
	public BAP getBilan(@RequestParam Long id) {
		return managerMetier.getBilan(id);
	}

	@RequestMapping(value = "/bilan", method = RequestMethod.PUT)
	public BAP updateBilan(@RequestBody BAP bilan) {
		return managerMetier.updateBilan(bilan);
	}

	@RequestMapping(value = "/objectif", method = RequestMethod.DELETE)
	public void deleteObjectif(@RequestParam Long id) {
		managerMetier.deleteObjectif(id);
	}

	@RequestMapping(value = "/baps", method = RequestMethod.GET)
	public Page<BAP> getAllBapManager(@RequestParam Long matricule, @RequestParam int page) {
		return managerMetier.getAllBapManager(matricule, page);
	}

	@RequestMapping(value = "/bapa", method = RequestMethod.POST)
	public Page<ArchiveBap> getAllArchiveBapManager(@RequestParam Long matricule, @RequestParam int page) {
		return managerMetier.getAllArchiveBapManager(matricule, page);
	}

	@RequestMapping(value = "/collaborateur", method = RequestMethod.GET)
	public List<Collaborateur> getAllCollaborateur() {
		return managerMetier.getAllCollaborateur();
	}

	@RequestMapping(value = "/objectif", method = RequestMethod.POST)
	public BAP addObjectif(@RequestBody Objectif objectif, @RequestParam Long idBap) {
		return managerMetier.addObjectif(objectif, idBap);
	}

	@RequestMapping(value = "/envoyer", method = RequestMethod.PUT)
	public BAP envoyerObjectifs(@RequestParam Long id) {
		return managerMetier.envoyerObjectifs(id);
	}

	@RequestMapping(value = "/lock", method = RequestMethod.PUT)
	public BAP openOrLockBap(@RequestParam Long id) {
		return managerMetier.openOrLockBap(id);
	}

	@RequestMapping(value = "/valider", method = RequestMethod.POST)
	public void validerBap(@RequestParam Long idBap) {
		managerMetier.validerBap(idBap);
	}

	@RequestMapping(value = "/annuler", method = RequestMethod.POST)
	public void AnnulerBap(@RequestParam Long idBap) {
		managerMetier.AnnulerBap(idBap);
	}

	@RequestMapping(value = "/encoursn", method = RequestMethod.GET)
	public Integer nombreEnCours(@RequestParam Long matricule) {
		return managerMetier.nombreEnCours(matricule);
	}

	@RequestMapping(value = "/rejeten", method = RequestMethod.GET)
	public Integer nombreRejete(@RequestParam Long matricule) {
		return managerMetier.nombreRejete(matricule);
	}

	@RequestMapping(value = "/enattenten", method = RequestMethod.GET)
	public Integer nombreEnAttente(@RequestParam Long matricule) {
		return managerMetier.nombreEnAttente(matricule);
	}

	@RequestMapping(value = "/archives", method = RequestMethod.GET)
	public Page<ArchiveBap> getAllArchiveBap(@RequestParam int page, @RequestParam Long matricule) {
		return managerMetier.getAllArchiveBap(matricule, page);
	}

}

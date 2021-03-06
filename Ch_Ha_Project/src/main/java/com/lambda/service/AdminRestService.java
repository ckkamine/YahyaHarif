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
import com.lambda.entities.Manager;
import com.lambda.entities.Objectif;
import com.lambda.entities.Projet;
import com.lambda.entities.User;
import com.lambda.metier.AdminMetier;

@RestController
@RequestMapping("/rest/admin")
public class AdminRestService {

	@Autowired
	AdminMetier adminMetier;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Page<User> getAllUsers(@RequestParam int page) {
		return adminMetier.getAllUsers(page);
	}

	@RequestMapping(value = "/baps", method = RequestMethod.GET)
	public Page<BAP> getAllBap(@RequestParam int page) {
		return adminMetier.getAllBap(page);
	}

	@RequestMapping(value = "/archives", method = RequestMethod.GET)
	public Page<ArchiveBap> getAllArchiveBap(@RequestParam int page) {
		return adminMetier.getAllArchiveBap(page);
	}

	@RequestMapping(value = "/projets", method = RequestMethod.GET)
	public Page<Projet> getAllProjets(@RequestParam int page) {
		return adminMetier.getAllProjets(page);
	}

	@RequestMapping(value = "/objectif", method = RequestMethod.POST)
	public BAP addObjectif(@RequestBody Objectif objectif, @RequestParam Long idBap) {
		return adminMetier.addObjectif(objectif, idBap);
	}

	@RequestMapping(value = "/bap", method = RequestMethod.POST)
	public BAP addBap(BAP bap) {
		return adminMetier.addBap(bap);
	}

	@RequestMapping(value = "/objectif", method = RequestMethod.GET)
	public Objectif getObjectif(@RequestParam Long id) {
		return adminMetier.getObjectif(id);
	}

	@RequestMapping(value = "/objectif", method = RequestMethod.PUT)
	public Objectif updateObjectif(@RequestBody Objectif objectif) {
		return adminMetier.updateObjectif(objectif);
	}

	@RequestMapping(value = "/objectif", method = RequestMethod.DELETE)
	public void deleteObjectif(@RequestParam Long id) {
		adminMetier.deleteObjectif(id);
	}

	@RequestMapping(value = "/projet", method = RequestMethod.POST)
	public Projet addProjet(@RequestBody Projet projet) {
		return adminMetier.addProjet(projet);
	}

	@RequestMapping(value = "/projet", method = RequestMethod.GET)
	public Projet getProjet(@RequestParam Long id) {
		return adminMetier.getProjet(id);
	}

	@RequestMapping(value = "/projet", method = RequestMethod.PUT)
	public Projet updateProjet(@RequestBody Projet projet) {
		return adminMetier.updateProjet(projet);
	}

	@RequestMapping(value = "/projet", method = RequestMethod.DELETE)
	public void deleteProjet(@RequestParam Long id) {
		adminMetier.deleteProjet(id);
	}

	@RequestMapping(value = "/bilan", method = RequestMethod.GET)
	public BAP getBilan(@RequestParam Long id) {
		return adminMetier.getBilan(id);
	}

	@RequestMapping(value = "/bilan", method = RequestMethod.PUT)
	public BAP updateBilan(@RequestBody BAP bilan) {
		return adminMetier.updateBilan(bilan);
	}

	@RequestMapping(value = "/usernames", method = RequestMethod.GET)
	public List<String> getAllUsername() {
		return adminMetier.getAllUsername();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User addUser(@RequestBody User user, @RequestParam Long matricule) {
		return adminMetier.addUser(user, matricule);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getUser(@RequestParam Long id) {
		return adminMetier.getUser(id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		return adminMetier.updateUser(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void deleteUser(@RequestParam Long id) {
		adminMetier.deleteUser(id);
	}

	@RequestMapping(value = "/userbymc", method = RequestMethod.GET)
	public Page<User> findByMcUser(@RequestParam String mc, @RequestParam int page) {
		return adminMetier.findByMcUser(mc, page);
	}

	@RequestMapping(value = "/bapbymc", method = RequestMethod.GET)
	public Page<BAP> findByMcBap(@RequestParam String mc, @RequestParam int page) {
		return adminMetier.findByMcBap(mc, page);
	}

	@RequestMapping(value = "/projetbymc", method = RequestMethod.GET)
	public Page<Projet> findByMcProjet(@RequestParam String mc, @RequestParam int page) {
		return adminMetier.findByMcProjet(mc, page);
	}

	@RequestMapping(value = "/encadrants", method = RequestMethod.GET)
	public List<Encadrant> getAllEncadrant() {
		return adminMetier.getAllEncadrant();
	}

	@RequestMapping(value = "/collaborateurs", method = RequestMethod.GET)
	public List<Collaborateur> getAllCollaborateur() {
		return adminMetier.getAllCollaborateur();
	}

	@RequestMapping(value = "/managers", method = RequestMethod.GET)
	public List<Manager> getAllManagers() {
		return adminMetier.getAllManagers();
	}

	@RequestMapping(value = "/envoyer", method = RequestMethod.PUT)
	public BAP envoyerObjectifs(@RequestParam Long id) {
		return adminMetier.envoyerObjectifs(id);
	}

	@RequestMapping(value = "/lock", method = RequestMethod.PUT)
	public BAP openOrLockBap(@RequestParam Long id) {
		return adminMetier.openOrLockBap(id);
	}

	@RequestMapping(value = "/valider", method = RequestMethod.POST)
	public void validerBap(@RequestParam Long idBap) {
		adminMetier.validerBap(idBap);
	}

	@RequestMapping(value = "/annuler", method = RequestMethod.POST)
	public void AnnulerBap(@RequestParam Long idBap) {
		adminMetier.AnnulerBap(idBap);
	}

	@RequestMapping(value = "/encoursn", method = RequestMethod.GET)
	public Integer nombreEnCours(@RequestParam Long matricule) {
		return adminMetier.nombreEnCours(matricule);
	}

	@RequestMapping(value = "/rejeten", method = RequestMethod.GET)
	public Integer nombreRejete(@RequestParam Long matricule) {
		return adminMetier.nombreRejete(matricule);
	}

	@RequestMapping(value = "/enattenten", method = RequestMethod.GET)
	public Integer nombreEnAttente(@RequestParam Long matricule) {
		return adminMetier.nombreEnAttente(matricule);
	}

}

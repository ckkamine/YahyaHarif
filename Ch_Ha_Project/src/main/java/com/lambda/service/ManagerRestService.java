package com.lambda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Objectif;
import com.lambda.metier.ManagerMetier;

@RestController
@RequestMapping("/rest/manager")
public class ManagerRestService {
	
	@Autowired
	ManagerMetier managerMetier;

	@RequestMapping(value="/bapc", method= RequestMethod.POST)
	public Page<BAP> getAllBapManager(@RequestBody Long matricule,@RequestParam int page) {
		return managerMetier.getAllBapManager(matricule, page);
	}

	@RequestMapping(value="/bapa", method= RequestMethod.POST)
	public Page<ArchiveBap> getAllArchiveBapManager(@RequestBody Long matricule,@RequestParam int page) {
		return managerMetier.getAllArchiveBapManager(matricule, page);
	}

	@RequestMapping(value="/bap", method= RequestMethod.POST)
	public BAP getBap(@RequestBody Long idBap) {
		return managerMetier.getBap(idBap);
	}

	@RequestMapping(value="/collaborateur", method= RequestMethod.GET)
	public List<Collaborateur> getAllCollaborateur() {
		return managerMetier.getAllCollaborateur();
	}

	@RequestMapping(value="/fermerbap", method= RequestMethod.POST)
	public void fermerBap(@RequestBody Long idBap) {
		managerMetier.fermerBap(idBap);
	}

	@RequestMapping(value="/ouvrirbap", method= RequestMethod.POST)
	public void ouvrirBap(@RequestBody Long idBap) {
		managerMetier.ouvrirBap(idBap);
	}

	@RequestMapping(value="/objectif/{idbap}", method= RequestMethod.POST)
	public Objectif addObjectif(@RequestBody Objectif objectif,@PathVariable("idbap") Long idBap) {
		return managerMetier.addObjectif(objectif, idBap);
	}

	@RequestMapping(value="/preparerbap", method= RequestMethod.POST)
	public BAP preparerBap(@RequestBody Long idBap) {
		return managerMetier.preparerBap(idBap);
	}

	@RequestMapping(value="/validerbap", method= RequestMethod.POST)
	public void validerBap(@RequestBody Long idBap) {
		managerMetier.validerBap(idBap);
	}
	
	

}

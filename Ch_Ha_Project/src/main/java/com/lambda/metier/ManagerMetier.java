package com.lambda.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import com.lambda.entities.ArchiveBap;
import com.lambda.entities.BAP;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Objectif;

public interface ManagerMetier {
	
	public Page<BAP> getAllBapManager(Long matricule, int page);
	public Page<ArchiveBap> getAllArchiveBapManager(Long matricule, int page);
	public BAP getBap(Long idBap);
	public List<Collaborateur> getAllCollaborateur();
	public void fermerBap(Long idBap);
	public void ouvrirBap(Long idBap);
	public BAP addObjectif(Objectif objectif, Long idBap);
	public BAP preparerBap(Long idBap);
	public void validerBap(Long idBap);
	public BAP addBap(BAP bap);
	public List<Encadrant> getAllEncadrant();
	public BAP getBilan(Long id);
	public BAP updateBilan(BAP bilan);
	public void deleteObjectif(Long id);
	public BAP envoyerObjectifs(Long id);
	public BAP openOrLockBap(Long id);

}

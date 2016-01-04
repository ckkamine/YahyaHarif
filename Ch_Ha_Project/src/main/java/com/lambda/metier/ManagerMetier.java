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

	public List<Collaborateur> getAllCollaborateur();

	public BAP addObjectif(Objectif objectif, Long idBap);

	public Page<ArchiveBap> getAllArchiveBap(Long matricule, int page);

	public List<Encadrant> getAllEncadrant();

	public BAP getBilan(Long id);

	public BAP updateBilan(BAP bilan);

	public void deleteObjectif(Long id);

	public BAP envoyerObjectifs(Long id);

	public BAP openOrLockBap(Long id);

	public void validerBap(Long idBap);

	public void AnnulerBap(Long idBap);

	public Integer nombreEnCours(Long matricule);

	public Integer nombreRejete(Long matricule);

	public Integer nombreEnAttente(Long matricule);
}

package com.lambda.metier;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lambda.entities.Collaborateur;
import com.lambda.entities.Feedback;
import com.lambda.entities.Projet;
import com.lambda.entities.Qualification;

public interface EncadrantMetier {
	
	public Page<Feedback> getFeedbacks(Long matricule, Integer page);
	public Feedback addFeedback(Feedback feedback);
	public List<Projet> getProjets(Long matricule);
	public List<Collaborateur> getCollaborateursCurrent(Long matricule);
	public void retirerFeedback(Long matricule, Long idFeedback);
}

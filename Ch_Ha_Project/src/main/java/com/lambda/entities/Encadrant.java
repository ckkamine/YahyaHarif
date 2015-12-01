package com.lambda.entities;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("ENC")
public class Encadrant extends User{
	
	@OneToMany(mappedBy="encadrant")
	private Collection<Feedback> feedbacks;
	@OneToMany(mappedBy="chefProjet")
	private Collection<Projet> projets;
	
	public Encadrant(){}

	public Encadrant(String username, String password, String email) {
		super(username, password, email);
		
	}

	public Collection<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Collection<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Encadrant(String username, String password, String firstName, String lastName, String email, String adresse,
			String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
	}

	public Collection<Projet> getProjets() {
		return projets;
	}

	public void setProjets(Collection<Projet> projets) {
		this.projets = projets;
	}

	
}

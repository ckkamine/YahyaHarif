package com.lambda.entities;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("ENC")
public class Encadrant extends User{
	

	@OneToMany(mappedBy="chefProjet")
	private Collection<Projet> projets;
	
	public Encadrant(){}

	public Encadrant(String username, String password, String email) {
		super(username, password, email);
		
	}


	public Encadrant(String username, String password, String firstName, String lastName, String email, String adresse,
			String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
	}

	@JsonIgnore
	public Collection<Projet> getProjets() {
		return projets;
	}

	public void setProjets(Collection<Projet> projets) {
		this.projets = projets;
	}

	
}

package com.lambda.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("ENC")
public class Encadrant extends User {

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ENC_COL", joinColumns = { @JoinColumn(name = "ENC") }, inverseJoinColumns = {
			@JoinColumn(name = "COL") })
	private List<Collaborateur> collaborateursCurrent;

	@OneToMany(mappedBy = "chefProjet")
	private Collection<Projet> projets;

	public Encadrant() {
		this.setRole(ROLE_EVALUATEUR);
	}

	public Encadrant(String username, String password, String email) {
		super(username, password, email);
		this.setRole(ROLE_EVALUATEUR);

	}

	public Encadrant(String username, String password, String firstName, String lastName, String email, String adresse,
			String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
		this.setRole(ROLE_EVALUATEUR);
	}

	@JsonIgnore
	public Collection<Projet> getProjets() {
		return projets;
	}

	public void setProjets(Collection<Projet> projets) {
		this.projets = projets;
	}

	@JsonIgnore
	public Collection<Collaborateur> getCollaborateursCurrent() {
		return collaborateursCurrent;
	}

	public void setCollaborateursCurrent(List<Collaborateur> collaborateursCurrent) {
		this.collaborateursCurrent = collaborateursCurrent;
	}

	public void addCollaborateur(Collaborateur c) {
		try {
			this.collaborateursCurrent.add(c);
		} catch (Exception e) {
			this.collaborateursCurrent = new ArrayList<Collaborateur>();
			this.collaborateursCurrent.add(c);
		}
	}
}

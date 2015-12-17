package com.lambda.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Projet implements Serializable{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idProjet;
	private String nom;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CHEF_ID")
	private Encadrant chefProjet;
	@ManyToMany(mappedBy="projets", fetch=FetchType.EAGER)
	private Collection<Collaborateur> collaborateurs;
	
	public Projet(String nom) {
		super();
		this.nom = nom;
	}

	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Encadrant getChefProjet() {
		return chefProjet;
	}

	public void setChefProjet(Encadrant chefProjet) {
		this.chefProjet = chefProjet;
	}

	@JsonIgnore
	public Collection<Collaborateur> getCollaborateurs() {
		return collaborateurs;
	}

	public void setCollaborateurs(Collection<Collaborateur> collaborateurs) {
		this.collaborateurs = collaborateurs;
	}
	
	

}

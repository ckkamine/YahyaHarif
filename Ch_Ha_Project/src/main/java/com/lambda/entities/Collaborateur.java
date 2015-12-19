package com.lambda.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.JoinColumn;

@Entity
@DiscriminatorValue("COL")
public class Collaborateur extends User{
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	@JoinColumn(name="BAP_ID")
	private BAP bap;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="PROJET_COL",
			joinColumns={@JoinColumn(name="COL_ID")},
		      inverseJoinColumns={@JoinColumn(name="PROJ_ID")})
	private Collection<Projet> projets;
	
	
	
	public Collaborateur(){
		this.setRole(ROLE_COLLABORATEUR);
	}

	public Collaborateur(String username, String password, String email) {
		super(username, password, email);
		
		this.setRole(ROLE_COLLABORATEUR);
	}

	public Collaborateur(String username, String password, String firstName, String lastName, String email,
			String adresse, String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
		
		this.setRole(ROLE_COLLABORATEUR);
	}


	
	
	
	public Collection<Projet> getProjets() {
		return projets;
	}

	public void setProjets(Collection<Projet> projets) {
		this.projets = projets;
	}


	

	@JsonIgnore
	public BAP getBap() {
		return bap;
	}

	@JsonSetter
	public void setBap(BAP bap) {
		this.bap = bap;
	}
	
	
	
}

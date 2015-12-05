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

import javax.persistence.JoinColumn;

@Entity
@DiscriminatorValue("COL")
public class Collaborateur extends User{
	
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="BAP_ID")
	private BAP bap;
	
	private Date dateProchainBap;
	@ManyToMany
	@JoinTable(name="PROJET_COL",
			joinColumns={@JoinColumn(name="COL_ID")},
		      inverseJoinColumns={@JoinColumn(name="PROJ_ID")})
	private Collection<Projet> projets;
	
	
	
	public Collaborateur(){
		
	}

	public Collaborateur(String username, String password, String email) {
		super(username, password, email);
		this.dateProchainBap= addYear(this.getDateRecrutement());
	}

	public Collaborateur(String username, String password, String firstName, String lastName, String email,
			String adresse, String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
		this.dateProchainBap= addYear(this.getDateRecrutement());
	}

	public Date addYear(Date d){
		Date r= d;
		r.setTime(r.getTime()+ 1000L * 60 * 60 * 24 * 365);
		 return r; 
	}

	
	public Date getDateProchainBap() {
		return dateProchainBap;
	}

	public void setDateProchainBap(Date dateProchainBap) {
		this.dateProchainBap = dateProchainBap;
	}

	@JsonIgnore
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

	public void setBap(BAP bap) {
		this.bap = bap;
	}
	
	
	
}

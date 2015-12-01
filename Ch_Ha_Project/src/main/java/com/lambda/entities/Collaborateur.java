package com.lambda.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@DiscriminatorValue("COL")
public class Collaborateur extends User{
	
	@OneToOne
	@JoinColumn(name="BAP_ID")
	private BAP bap;
	
	private Date dateProchainBap;
	@ManyToMany
	@JoinTable(name="PROJET_COL",
			joinColumns={@JoinColumn(name="COL_ID")},
		      inverseJoinColumns={@JoinColumn(name="PROJ_ID")})
	private Collection<Projet> projets;
	 @OneToMany
	  @JoinTable
	  (   name="COL_OBJECTIFS",
	      joinColumns={ @JoinColumn(name="COL_ID") },
	      inverseJoinColumns={ @JoinColumn(name="OBJECTIF_ID") }
	  )
	private Collection<Objectif> objectifs;
	private String posteActuel;
	
	public Collaborateur(){
		
	}

	public Collaborateur(String username, String password, String email) {
		super(username, password, email);
		this.dateProchainBap= addYear(getDateRecrutement());
	}

	public Collaborateur(String username, String password, String firstName, String lastName, String email,
			String adresse, String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
		this.dateProchainBap= addYear(getDateRecrutement());
	}

	public Date addYear(Date d){
		 d.setTime(d.getTime()+ 1000L * 60 * 60 * 24 * 365);
		 return d;
	}

	
	public Date getDateProchainBap() {
		return dateProchainBap;
	}

	public void setDateProchainBap(Date dateProchainBap) {
		this.dateProchainBap = dateProchainBap;
	}

	public Collection<Projet> getProjets() {
		return projets;
	}

	public void setProjets(Collection<Projet> projets) {
		this.projets = projets;
	}

	public Collection<Objectif> getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(Collection<Objectif> objectifs) {
		this.objectifs = objectifs;
	}

	public String getPosteActuel() {
		return posteActuel;
	}

	public void setPosteActuel(String posteActuel) {
		this.posteActuel = posteActuel;
	}

	public BAP getBap() {
		return bap;
	}

	public void setBap(BAP bap) {
		this.bap = bap;
	}
	
	
	
}

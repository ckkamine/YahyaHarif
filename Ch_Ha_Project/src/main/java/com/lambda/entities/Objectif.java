package com.lambda.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;;

@Entity
public class Objectif implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idObjectif;
	private String nom;
	private Date dateCreation;
	private String type;
	private boolean archive;
	@OneToMany
	  @JoinColumn(name="OBJEC_ID")
	private Collection<Description> descriptions;
	
	
	public Objectif() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Objectif(String nom, String type) {
		super();
		this.nom = nom;
		this.type= type;
		this.dateCreation= new Date();
		this.archive= false;
	}



	public boolean isArchive() {
		return archive;
	}



	public void setArchive(boolean archive) {
		this.archive = archive;
	}



	public Long getIdObjectif() {
		return idObjectif;
	}
	public void setIdObjectif(Long idObjectif) {
		this.idObjectif = idObjectif;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Collection<Description> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(Collection<Description> descriptions) {
		this.descriptions = descriptions;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Date getDateCreation() {
		return dateCreation;
	}



	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
	
}

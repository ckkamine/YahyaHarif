package com.lambda.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
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
	private String objectif;
	private Date dateCreation;
	private String categorie;
	private boolean valide;
	private boolean archive;
	@ManyToOne
	@JoinColumn(name="COL_ID")
	private User employe;
	@OneToMany(cascade=CascadeType.REMOVE)
	  @JoinColumn(name="OBJEC_ID")
	private Collection<Description> descriptions;
	
	
	public Objectif() {
		super();
		this.archive=false;
	}
	
	
	
	public Objectif(String objectif, String categorie) {
		super();
		this.objectif = objectif;
		this.categorie= categorie;
		this.dateCreation= new Date();
		this.archive= false;
	}



	public boolean isArchive() {
		return archive;
	}

	


	public User getEmploye() {
		return employe;
	}



	public void setEmploye(User employe) {
		this.employe = employe;
	}



	public String getObjectif() {
		return objectif;
	}



	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}



	public String getCategorie() {
		return categorie;
	}



	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}



	public boolean isValide() {
		return valide;
	}



	public void setValide(boolean valide) {
		this.valide = valide;
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
		return objectif;
	}
	public void setNom(String nom) {
		this.objectif = nom;
	}
	public Collection<Description> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(Collection<Description> descriptions) {
		this.descriptions = descriptions;
	}



	public String getType() {
		return categorie;
	}



	public void setType(String type) {
		this.categorie = type;
	}



	public Date getDateCreation() {
		return dateCreation;
	}



	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
	
}

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
@Entity
class Description implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDescription;
	private String description;
	private String mesure;
	@ManyToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="RESP_ID")
	private Encadrant responsable;
	private Integer poids;
	private Integer note;
	
	
	public Long getIdDescription() {
		return idDescription;
	}
	public void setIdDescription(Long idDescription) {
		this.idDescription = idDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMesure() {
		return mesure;
	}
	public void setMesure(String mesure) {
		this.mesure = mesure;
	}
	public Encadrant getResponsable() {
		return responsable;
	}
	public void setResponsable(Encadrant responsable) {
		this.responsable = responsable;
	}
	public Integer getPoids() {
		return poids;
	}
	public void setPoids(Integer poids) {
		this.poids = poids;
	}
	public Integer getNote() {
		return note;
	}
	public void setNote(Integer note) {
		this.note = note;
	}
	public Description() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Description(String description, String mesure,  Integer poids, Integer note) {
		super();
		this.description = description;
		this.mesure = mesure;
		this.poids = poids;
		this.note = note;
	}
	public Description(String description, String mesure, Encadrant responsable, Integer poids, Integer note) {
		super();
		this.description = description;
		this.mesure = mesure;
		this.responsable = responsable;
		this.poids = poids;
		this.note = note;
	}
	
	
}
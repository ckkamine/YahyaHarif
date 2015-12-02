package com.lambda.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Description implements Serializable{
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
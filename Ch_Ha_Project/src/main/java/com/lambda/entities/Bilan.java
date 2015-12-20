package com.lambda.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Inheritance
@DiscriminatorColumn(name="TYPE",discriminatorType=DiscriminatorType.STRING, length=3)

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
		@Type(name="BAP", value=BAP.class),
		@Type(name="BIP", value=BIP.class)
})
public abstract class Bilan implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date dateBilan;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="COLL_ID")
	private Collaborateur collaborateur;
	@OneToMany(fetch=FetchType.LAZY, cascade= CascadeType.MERGE)
	@JoinColumn(name="B_ID_E")
	private List<Objectif> objectifsEntrantes;
	private String decision;
	public Bilan() {
		super();
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateBip() {
		return dateBilan;
	}
	public void setDateBip(Date dateBip) {
		this.dateBilan = dateBip;
	}
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}
	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	public Date getDateBilan() {
		return dateBilan;
	}
	public void setDateBilan(Date dateBilan) {
		this.dateBilan = dateBilan;
	}
	

	public List<Objectif> getObjectifsEntrantes() {
		return objectifsEntrantes;
	}

	public void setObjectifsEntrantes(List<Objectif> objectifsEntrantes) {
		this.objectifsEntrantes = objectifsEntrantes;
	}

	public Bilan(Date dateBilan, Collaborateur collaborateur) {
		super();
		this.dateBilan = dateBilan;
		this.collaborateur = collaborateur;
	}
	
	

}

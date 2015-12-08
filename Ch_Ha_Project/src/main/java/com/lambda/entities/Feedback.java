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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Feedback implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFeedback;
	@ManyToOne
		@JoinColumn(name="COL_ID")
	private Collaborateur collaborateur;
	@ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="ENCA_ID")
	private Encadrant encadrant;
	@ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="PROJET_ID")
	private Projet projet;
	private Date debutInter;
	private Date finInter;
	private String role;
	private String commentaire;
	private Integer nombreThemeCalifie;
	private Integer totalPoids;
	private double noteGlobal;
	private Integer nombreJourValorise;
	@OneToMany(cascade=CascadeType.REMOVE)
	  @JoinColumn(name="FEED_ID")
	private Collection<Qualification> qualifications;
	private boolean archive;
	
	
	public Feedback() {
		super();
		this.archive=false;
	}
	public Feedback(Date debutInter, Date finInter, String role, String commentaire, Integer nombreJourValorise) {
		super();
		this.debutInter = debutInter;
		this.finInter = finInter;
		this.role = role;
		this.commentaire = commentaire;
		this.nombreJourValorise = nombreJourValorise;
		this.archive= false;
	}
	public Long getIdFeedback() {
		return idFeedback;
	}
	public void setIdFeedback(Long idFeedback) {
		this.idFeedback = idFeedback;
	}
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}
	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}
	@JsonIgnore
	public Encadrant getEncadrant() {
		return encadrant;
	}
	public void setEncadrant(Encadrant encadrant) {
		this.encadrant = encadrant;
	}
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	public Date getDebutInter() {
		return debutInter;
	}
	public void setDebutInter(Date debutInter) {
		this.debutInter = debutInter;
	}
	public Date getFinInter() {
		return finInter;
	}
	public void setFinInter(Date finInter) {
		this.finInter = finInter;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Integer getNombreThemeCalifie() {
		return nombreThemeCalifie;
	}
	public void setNombreThemeCalifie(Integer nombreThemeCalifie) {
		this.nombreThemeCalifie = nombreThemeCalifie;
	}
	public Integer getTotalPoids() {
		return totalPoids;
	}
	public void setTotalPoids(Integer totalPoids) {
		this.totalPoids = totalPoids;
	}
	public double getNoteGlobal() {
		return noteGlobal;
	}
	public void setNoteGlobal(double noteGlobal) {
		this.noteGlobal = noteGlobal;
	}
	public Integer getNombreJourValorise() {
		return nombreJourValorise;
	}
	public void setNombreJourValorise(Integer nombreJourValorise) {
		this.nombreJourValorise = nombreJourValorise;
	}
	public Collection<Qualification> getQualifications() {
		return qualifications;
	}
	public void setQualifications(Collection<Qualification> qualifications) {
		this.qualifications = qualifications;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	
	
}

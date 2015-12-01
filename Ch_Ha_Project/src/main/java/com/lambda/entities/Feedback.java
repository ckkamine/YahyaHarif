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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Feedback implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFeedback;
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
	@OneToMany
	  @JoinColumn(name="FEED_ID")
	private Collection<Qualification> qualifications;
	
	
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(Date debutInter, Date finInter, String role, String commentaire, Integer nombreJourValorise) {
		super();
		this.debutInter = debutInter;
		this.finInter = finInter;
		this.role = role;
		this.commentaire = commentaire;
		this.nombreJourValorise = nombreJourValorise;
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
	
	
	
}
@Entity
class Qualification implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idQualification;
	private String theme;
	private String qualification;
	private String remarque;
	
	public Qualification(){}

	public Qualification(String theme, String qualification, String remarque) {
		super();
		this.theme = theme;
		this.qualification = qualification;
		this.remarque = remarque;
	}

	public Long getIdQualification() {
		return idQualification;
	}

	public void setIdQualification(Long idQualification) {
		this.idQualification = idQualification;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	
	
	
}
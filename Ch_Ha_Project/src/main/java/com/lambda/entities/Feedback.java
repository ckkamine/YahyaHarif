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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Feedback implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFeedback;
	@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="COL_ID")
	private Collaborateur collaborateur;
	@ManyToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="ENCA_ID")
	private Encadrant encadrant;
	@ManyToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="PROJET_ID")
	private Projet projet;
	private Date debutInter;
	private Date finInter;
	private String role;
	@Lob
	private String commentaire;
	private Integer nombreThemeCalifie;
	private Integer totalPoids;
	private double noteGlobal;
	private Integer nombreJourValorise;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	  @JoinColumn(name="FEED_ID")
	private Collection<Qualification> qualifications;
	private Date dateFeedback;
	private boolean archive;
	private boolean locked;
	
	
	public Feedback() {
		super();
		this.archive=false;
		this.dateFeedback= new Date();
		this.locked= true;
	}
	public Feedback(Date debutInter, Date finInter, String role, String commentaire, Integer nombreJourValorise) {
		super();
		this.debutInter = debutInter;
		this.finInter = finInter;
		this.role = role;
		this.commentaire = commentaire;
		this.nombreJourValorise = nombreJourValorise;
		this.archive= false;
		this.dateFeedback= new Date();
		this.locked= true;
	}
	
	
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
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
	
	public Date getDateFeedback() {
		return dateFeedback;
	}
	public void setDateFeedback(Date dateFeedback) {
		this.dateFeedback = dateFeedback;
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

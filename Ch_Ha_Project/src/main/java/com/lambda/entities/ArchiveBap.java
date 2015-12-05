package com.lambda.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ArchiveBap implements Serializable{
	@Id
	private Long idBap;
	private Date dateBilan;
	@ManyToOne
	@JoinColumn(name="COL_ID")
	private Collaborateur collaborateur;
	@OneToMany
	@JoinColumn(name="AR_BAP_ID")
	private Collection<Objectif> objectifsEntrantes;
	private String decision;
	@OneToMany
	@JoinColumn(name="AR_BAP_ID")
	private Collection<Feedback> feedbacks;
	private boolean locked;
	@OneToMany
	@JoinColumn(name="AR_BAP_ID")
	private Collection<Objectif> objectifsSortantes;
	@ManyToOne
	@JoinColumn(name="MAG_ID")
	private Manager manager;
	
	public ArchiveBap(){}

	public ArchiveBap(Long idBap, Date dateBilan, Collaborateur collaborateur, Collection<Objectif> objectifsEntrantes,
			String decision, Collection<Feedback> feedbacks, boolean locked, Collection<Objectif> objectifsSortantes,
			Manager manger) {
		super();
		this.idBap = idBap;
		this.dateBilan = dateBilan;
		this.collaborateur = collaborateur;
		this.objectifsEntrantes = objectifsEntrantes;
		this.decision = decision;
		this.feedbacks = feedbacks;
		this.locked = locked;
		this.objectifsSortantes = objectifsSortantes;
		this.manager = manger;
	}

	public Long getIdBap() {
		return idBap;
	}

	public void setIdBap(Long idBap) {
		this.idBap = idBap;
	}

	public Date getDateBilan() {
		return dateBilan;
	}

	public void setDateBilan(Date dateBilan) {
		this.dateBilan = dateBilan;
	}

	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	public Collection<Objectif> getObjectifsEntrantes() {
		return objectifsEntrantes;
	}

	public void setObjectifsEntrantes(Collection<Objectif> objectifsEntrantes) {
		this.objectifsEntrantes = objectifsEntrantes;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public Collection<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Collection<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Collection<Objectif> getObjectifsSortantes() {
		return objectifsSortantes;
	}

	public void setObjectifsSortantes(Collection<Objectif> objectifsSortantes) {
		this.objectifsSortantes = objectifsSortantes;
	}

	public Manager getManger() {
		return manager;
	}

	public void setManger(Manager manger) {
		this.manager = manger;
	}
	
	

}

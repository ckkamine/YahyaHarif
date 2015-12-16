package com.lambda.entities;

import java.util.Collection;
import java.util.Date;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@DiscriminatorValue("BAP")
public class BAP extends Bilan{
	
	public static final String EN_ATTENTE= "En attente";
	public static final String EN_COURS= "En cours";
	public static final String VALIDE= "Validé";
	public static final String ANNULE= "Annulé";
	public static final String REJETE= "Rejeté";

	@OneToMany
	  @JoinColumn(name="BAP_ID")
	private Collection<Feedback> feedbacks;
	private boolean locked;
	@OneToMany
	  @JoinColumn(name="BAP_ID_S")
	private Collection<Objectif> objectifsSortantes;
	private String status;
	@Cascade(value = { CascadeType.DELETE })
	@ManyToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="MAG_ID")
	private Manager manager;
	private int compteur;
	
	public BAP(){
		this.status= EN_ATTENTE;
	}
	
	

	public BAP(Date dateBilan, Collaborateur collaborateur,   boolean locked, Manager manager) {
		super(dateBilan, collaborateur);
		this.locked = true;
		this.status = EN_ATTENTE;
		this.manager = manager;
		this.compteur = 0;
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
	
	

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public static String getRejete() {
		return REJETE;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public static String getEnAttente() {
		return EN_ATTENTE;
	}

	public static String getEnCours() {
		return EN_COURS;
	}

	public static String getValide() {
		return VALIDE;
	}

	public static String getAnnule() {
		return ANNULE;
	}

	
}

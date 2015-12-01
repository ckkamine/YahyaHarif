package com.lambda.entities;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("BAP")
public class BAP extends Bilan{
	
	public static final String EN_ATTENTE= "En attente";
	public static final String EN_COURS= "En cours";
	public static final String VALIDE= "Validé";
	public static final String ANNULE= "Annulé";

	@OneToMany
	  @JoinColumn(name="BAP_ID")
	private Collection<Feedback> feedbacks;
	private boolean locked;
	@OneToMany
	  @JoinColumn(name="BAP_ID_S")
	private Collection<Objectif> objectifsSortantes;
	private String status;
	@ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="MAG_ID")
	private Manager manager;
	
	public BAP(){}

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

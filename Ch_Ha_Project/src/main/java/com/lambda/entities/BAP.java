package com.lambda.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.lambda.entities.Objectif;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("BAP")
public class BAP extends Bilan implements Serializable {

	public static final String EN_ATTENTE = "En attente";
	public static final String EN_COURS = "En cours";
	public static final String VALIDE = "Validé";
	public static final String ANNULE = "Annulé";
	public static final String REJETE = "Rejeté";

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "BAP_ID")
	private List<Feedback> feedbacks;
	private boolean locked;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@LazyToOne(LazyToOneOption.FALSE)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "BAP_ID_S")
	private List<Objectif> objectifsSortantes;
	private String status;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAG_ID")
	private Manager manager;
	private int compteur;
	private Integer noteGlobale;
	private boolean envoye;

	public BAP() {
		this.status = EN_ATTENTE;
		this.envoye = false;
		this.compteur = 0;
		this.locked = true;
	}

	public BAP(Date dateBilan, Collaborateur collaborateur, Manager manager) {
		super(dateBilan, collaborateur);
		this.locked = true;
		this.status = EN_ATTENTE;
		this.manager = manager;
		this.compteur = 0;
		this.envoye = false;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public List<Objectif> getObjectifsSortantes() {
		return objectifsSortantes;
	}

	public void setObjectifsSortantes(List<Objectif> objectifsSortantes) {
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

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public Integer getNoteGlobale() {
		return noteGlobale;
	}

	public void setNoteGlobale(Integer noteGlobale) {
		this.noteGlobale = noteGlobale;
	}

	public boolean isEnvoye() {
		return envoye;
	}

	public void setEnvoye(boolean envoye) {
		this.envoye = envoye;
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

	public static String getRejete() {
		return REJETE;
	}

	public void addFeedback(Feedback f) {
		try {
			this.feedbacks.add(f);
		} catch (Exception e) {
			this.feedbacks = new ArrayList<Feedback>();
			this.feedbacks.add(f);
		}
	}

	public void addObjectifSortantes(Objectif o) {
		try {
			this.objectifsSortantes.add(o);
		} catch (Exception e) {
			this.objectifsSortantes = new ArrayList<Objectif>();
			this.objectifsSortantes.add(o);
		}
	}

}

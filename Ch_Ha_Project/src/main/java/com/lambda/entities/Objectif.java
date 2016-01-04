package com.lambda.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Objectif implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idObjectif;
	private String objectif;
	private Date dateCreation;
	private String categorie;
	private boolean valide;
	private boolean archive;
	@ManyToOne
	@JoinColumn(name = "COL_ID")
	private User employe;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "OBJEC_ID")
	private Collection<Description> descriptions;

	public Objectif() {
		super();
		this.archive = false;
	}

	public Objectif(String objectif, String categorie) {
		super();
		this.objectif = objectif;
		this.categorie = categorie;
		this.dateCreation = new Date();
		this.archive = false;
	}

	public Long getIdObjectif() {
		return idObjectif;
	}

	public void setIdObjectif(Long idObjectif) {
		this.idObjectif = idObjectif;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public User getEmploye() {
		return employe;
	}

	public void setEmploye(User employe) {
		this.employe = employe;
	}

	public Collection<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Collection<Description> descriptions) {
		this.descriptions = descriptions;
	}

	public void addDescription(Description d) {
		try {
			this.getDescriptions().add(d);
		} catch (Exception e) {
			this.descriptions = new ArrayList<Description>();
			this.descriptions.add(d);
		}
	}

}

package com.lambda.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Qualification implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idQualification;
	private String theme;
	private String qualification;
	private String remarque;

	public Qualification() {
	}

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
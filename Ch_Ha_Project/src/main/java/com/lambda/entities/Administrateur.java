package com.lambda.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("ADM")
public class Administrateur extends Manager {

	public Administrateur() {
		this.setRole(ROLE_ADMIN);
	}

	public Administrateur(String username, String password, String firstName, String lastName, String email,
			String adresse, String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
		this.setRole(ROLE_ADMIN);
	}

	public Administrateur(String username, String password, String email) {
		super(username, password, email);
		this.setRole(ROLE_ADMIN);
	}

}

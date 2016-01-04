package com.lambda.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("MAG")
public class Manager extends User {

	public Manager() {
		this.setRole(ROLE_MANAGER);
	}

	public Manager(String username, String password, String email) {
		super(username, password, email);
		this.setRole(ROLE_MANAGER);
	}

	public Manager(String username, String password, String firstName, String lastName, String email, String adresse,
			String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
		this.setRole(ROLE_MANAGER);
	}

}

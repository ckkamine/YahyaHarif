package com.lambda.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADM")
public class Administrateur extends Manager{
	
	
	
	public Administrateur(){}

	public Administrateur(String username, String password, String firstName, String lastName, String email,
			String adresse, String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
		// TODO Auto-generated constructor stub
	}

	public Administrateur(String username, String password, String email) {
		super(username, password, email);
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}

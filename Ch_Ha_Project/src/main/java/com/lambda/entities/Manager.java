package com.lambda.entities;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("MAG")
public class Manager extends User{
	
	@OneToMany(mappedBy= "manager", fetch= FetchType.EAGER)
	private Collection<BAP> baps;
	
	public Manager(){}

	public Manager(String username, String password, String email) {
		super(username, password, email);
	}

	public Manager(String username, String password, String firstName, String lastName, String email, String adresse,
			String telephone) {
		super(username, password, firstName, lastName, email, adresse, telephone);
	}

	public Collection<BAP> getBaps() {
		return baps;
	}

	public void setBaps(Collection<BAP> baps) {
		this.baps = baps;
	}
	

}

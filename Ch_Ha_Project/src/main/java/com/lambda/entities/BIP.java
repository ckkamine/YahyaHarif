package com.lambda.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BIP")
public class BIP extends Bilan{
	
	public BIP(){}

	public BIP(Date dateBilan, Collaborateur collaborateur, Collection<Objectif> objectifsEntrantes) {
		super(dateBilan, collaborateur, objectifsEntrantes);
		// TODO Auto-generated constructor stub
	}
	
}

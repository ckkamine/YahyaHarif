package com.lambda.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("BIP")
public class BIP extends Bilan {

	public BIP() {
	}

	public BIP(Date dateBilan, Collaborateur collaborateur, Collection<Objectif> objectifsEntrantes) {
		super(dateBilan, collaborateur);
		// TODO Faire le BIP
	}

}

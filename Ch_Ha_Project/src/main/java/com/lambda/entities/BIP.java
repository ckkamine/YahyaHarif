package com.lambda.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BIP")
public class BIP extends Bilan{
	
	public BIP(){}
}

package com.lambda.metier;

import com.lambda.entities.User;

public interface UserMetier {

	public User updatePassword(Long matricule, String password);
	User findByUsername(String name);
	
}

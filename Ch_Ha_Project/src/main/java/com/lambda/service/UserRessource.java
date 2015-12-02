package com.lambda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.entities.User;
import com.lambda.metier.UserMetier;

@RestController
public class UserRessource {

	@Autowired
	UserMetier userMetier;
	
	@RequestMapping(value="/test/{id}/{mdp}")
	public User updatePassword(@PathVariable(value="mdp") String mdp, @PathVariable(value="id") Long id){
		return userMetier.updatePassword(id, mdp);
	}
	
}

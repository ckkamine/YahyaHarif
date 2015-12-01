package com.lambda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.entities.Administrateur;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.User;
import com.lambda.repository.ColRepository;
import com.lambda.repository.UserRepository;
import com.lambda.repository.adminRepo;

@RestController
public class testController {

	@Autowired
	private UserRepository u;
	
	@Autowired
	private ColRepository c;
	
	@Autowired
	private adminRepo a;
	
	@RequestMapping("/test")
	public String test(){
		return "test";
	}
	
	@RequestMapping("/user")
	public List<User> user(){
		return u.findAll();
	}
	
	@RequestMapping("/col")
	public List<Collaborateur> col(){
		return c.findAll();
	}
	
	@RequestMapping("/admin")
	public List<Administrateur> admin(){
		return a.findAll();
	}
}

package com.lambda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lambda.entities.Administrateur;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.User;
import com.lambda.repository.ColRepository;
import com.lambda.repository.UserRepository;
import com.lambda.repository.adminRepo;




@Component
public class DataBaseInit {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ColRepository cl;
    
    @Autowired
    private adminRepo a;

    @Autowired
    public DataBaseInit(UserRepository userDao, ColRepository cr, adminRepo a) {
		this.userRepository = userDao;
		this.cl= cr;
		this.a= a;
	} 
    
	public void init () {
		
		
		}
	}



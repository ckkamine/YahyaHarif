package com.lambda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Feedback;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.EncadrantRepository;
import com.lambda.repository.FeedbackRepository;




@Component
public class DataBaseInit {
	
	@Autowired
	CollaborateurRepository cR;
	@Autowired
	EncadrantRepository eR;
	@Autowired
	FeedbackRepository fR;
   
		@Autowired
		public DataBaseInit(CollaborateurRepository userDao, EncadrantRepository eR, FeedbackRepository fR) {
			this.cR= userDao;
			this.eR= eR;
			this.fR= fR;
		}
//		this.userRepository = userDao;
//		this.cl= cr;
//		this.a= a;
//	} 
    
	public void init () {
		List<Feedback> list= new ArrayList<Feedback>();
		Collaborateur c= new Collaborateur("username", "password", "email");
		Encadrant e= new Encadrant("username", "password", "email");
		cR.save(c);
		eR.save(e);
		for(int i=0; i<4 ; i++){
			Feedback f= new Feedback(new Date(), new Date(), "role", "commentaire", i);
			if(i>0) f.setEncadrant(e);
			list.add(f);
			fR.save(f);
		}
		
		
	}
	
}



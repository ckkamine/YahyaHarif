package com.lambda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.lambda.entities.BAP;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Feedback;
import com.lambda.entities.Manager;
import com.lambda.entities.Objectif;
import com.lambda.metier.ManagerMetier;
import com.lambda.repository.BapRepository;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.EncadrantRepository;
import com.lambda.repository.FeedbackRepository;
import com.lambda.repository.ManagerRepository;
import com.lambda.repository.ObjectifRepository;




@Component
public class DataBaseInit {
	
	@Autowired
	CollaborateurRepository cR;
	@Autowired
	EncadrantRepository eR;
	@Autowired
	FeedbackRepository fR;
	@Autowired
	ObjectifRepository or;
	@Autowired
	ManagerRepository mR;
	@Autowired
	BapRepository br;
	
	@Autowired
	PasswordEncoder passwordEncoder;
   
		@Autowired
		public DataBaseInit(CollaborateurRepository userDao, EncadrantRepository eR, FeedbackRepository fR, ObjectifRepository or
				, BapRepository br, ManagerRepository mR, PasswordEncoder passwordEncoder) {
			this.cR= userDao;
			this.eR= eR;
			this.fR= fR;
			this.or= or;
			this.br= br;
			this.mR= mR;
			this.passwordEncoder= passwordEncoder;
		}
//		this.userRepository = userDao;
//		this.cl= cr;
//		this.a= a;
//	} 
    
	public void init () {
		List<Feedback> list= new ArrayList<Feedback>();
		Collaborateur c= new Collaborateur("collaborateur", passwordEncoder.encode("collaborateur"), "email");
		Encadrant e= new Encadrant("encadrant", passwordEncoder.encode("encadrant"), "email");
		cR.save(c);
		eR.save(e);
		Manager m= new Manager("manager", passwordEncoder.encode("manager"), "manager@lambda.com");
		mR.save(m);
		for(int i=0; i<4 ; i++){
			Feedback f= new Feedback(new Date(), new Date(), "role", "commentaire", i);
			if(i>0) f.setEncadrant(e);
			list.add(f);
			fR.save(f);
		}
		BAP b= new BAP(new Date(), c, true, "En attente", m, 0);
		br.save(b);
		Objectif o=  new Objectif("nom", "type");
		o.setEmploye(c);
		o.setArchive(true);
		or.save(o);
		Objectif o1=  new Objectif("nom", "type");
		o1.setEmploye(c);
		o1.setArchive(false);
		or.save(o1);
		
	}
	
}



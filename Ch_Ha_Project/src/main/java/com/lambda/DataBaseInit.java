package com.lambda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.neo4j.cypher.internal.compiler.v2_1.planner.logical.findShortestPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.lambda.entities.Administrateur;
import com.lambda.entities.BAP;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Feedback;
import com.lambda.entities.Manager;
import com.lambda.entities.Objectif;
import com.lambda.entities.Projet;
import com.lambda.metier.ManagerMetier;
import com.lambda.repository.AdminRepository;
import com.lambda.repository.BapRepository;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.EncadrantRepository;
import com.lambda.repository.FeedbackRepository;
import com.lambda.repository.ManagerRepository;
import com.lambda.repository.ObjectifRepository;
import com.lambda.repository.ProjetRepository;




@Component
public class DataBaseInit {
	@Autowired
	AdminRepository aR;
	
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
	ProjetRepository projetRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
   
		@Autowired
		public DataBaseInit(CollaborateurRepository userDao, EncadrantRepository eR, FeedbackRepository fR, ObjectifRepository or
				, BapRepository br, ManagerRepository mR, PasswordEncoder passwordEncoder, AdminRepository aR, ProjetRepository projetRepository) {
			this.cR= userDao;
			this.eR= eR;
			this.fR= fR;
			this.or= or;
			this.br= br;
			this.mR= mR;
			this.passwordEncoder= passwordEncoder;
			this.aR= aR;
			this.projetRepository= projetRepository;
		}

    
	public void init () {
		Administrateur a= new Administrateur("admin", passwordEncoder.encode("admin"), "choukoukouamine@gmail.com");
		aR.save(a);
		Collaborateur c= new Collaborateur("collaborateur", passwordEncoder.encode("collaborateur"), "email");
		cR.save(c);
		Encadrant e= new Encadrant("encadrant", passwordEncoder.encode("encadrant"), "choukoukouamine@outlook.com");
		eR.save(e);
		BAP b= new BAP(new Date(116, 0, 1), c, true, a);
		br.save(b);
		Administrateur a1= new Administrateur("admin", passwordEncoder.encode("admin"), "choukoukouamine@gmail.com");
		aR.save(a1);
		Collaborateur c1= new Collaborateur("collaborateur", passwordEncoder.encode("collaborateur"), "email");
		cR.save(c1);
		Encadrant e1= new Encadrant("encadrant", passwordEncoder.encode("encadrant"), "choukoukouamine@outlook.com");
		eR.save(e1);
		BAP b1= new BAP(new Date(115, 11, 1), c1, true, a1);
		br.save(b1);
		Administrateur ad= new Administrateur("admin1", passwordEncoder.encode("admin1"), "choukoukouamine@outlook.com");
		aR.save(ad);
		Collaborateur co= new Collaborateur("collaborateur1", passwordEncoder.encode("collaborateur1"), "email");
		cR.save(co);
		Encadrant en= new Encadrant("encadrant1", passwordEncoder.encode("encadrant1"), "choukoukouamine@gmail.com");
		eR.save(en);
		BAP ba= new BAP(new Date(115, 11, 1), co, true, ad);
		br.save(ba);
		int i=0;
		co.setProjets(new ArrayList<>());
		c.setProjets(new ArrayList<>());
		c1.setProjets(new ArrayList<>());
		while (i<3) {
			Projet p= new Projet("projet "+i);
			if(i<1){
				p.setChefProjet(e);
			}else{
				p.setChefProjet(en);
			}
			p.setCollaborateurs(new ArrayList<Collaborateur>());
			p.getCollaborateurs().add(c);
			projetRepository.save(p);
			c.getProjets().add(p);
			cR.save(c);
			p.getCollaborateurs().add(co);
			projetRepository.save(p);
			co.getProjets().add(p);
			cR.save(co);
			p.getCollaborateurs().add(c1);
			projetRepository.save(p);
			c1.getProjets().add(p);
			cR.save(c1);
			i++;
		}
		i=0;
		while (i<3) {
			Objectif p= new Objectif("objectif"+i, "categorie");
			p.setEmploye(c);
			or.save(p);
			i++;
		}
		System.out.println("----------------------- Fin------------------");
		
	}
	
	
}



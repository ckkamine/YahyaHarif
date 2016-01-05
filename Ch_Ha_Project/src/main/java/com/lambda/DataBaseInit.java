package com.lambda;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.lambda.entities.Administrateur;
import com.lambda.entities.BAP;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Description;
import com.lambda.entities.Encadrant;
import com.lambda.entities.Manager;
import com.lambda.entities.Objectif;
import com.lambda.entities.Projet;
import com.lambda.repository.AdminRepository;
import com.lambda.repository.BapRepository;
import com.lambda.repository.CollaborateurRepository;
import com.lambda.repository.DescriptionRepository;
import com.lambda.repository.EncadrantRepository;
import com.lambda.repository.FeedbackRepository;
import com.lambda.repository.ManagerRepository;
import com.lambda.repository.ObjectifRepository;
import com.lambda.repository.ProjetRepository;

//Initialisation BD
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
	DescriptionRepository descriptionRepository;
	@Autowired
	ProjetRepository projetRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public DataBaseInit(CollaborateurRepository userDao, EncadrantRepository eR, FeedbackRepository fR,
			ObjectifRepository or, BapRepository br, ManagerRepository mR, PasswordEncoder passwordEncoder,
			AdminRepository aR, ProjetRepository projetRepository, DescriptionRepository descriptionRepository) {
		this.cR = userDao;
		this.eR = eR;
		this.fR = fR;
		this.or = or;
		this.br = br;
		this.mR = mR;
		this.passwordEncoder = passwordEncoder;
		this.aR = aR;
		this.projetRepository = projetRepository;
		this.descriptionRepository = descriptionRepository;
	}

	// Fonction d'Initialisation
	@SuppressWarnings("static-access")
	public void init() {
		Administrateur a = new Administrateur("admin1", passwordEncoder.encode("admin1"), "AMINE", "CHOUKOUKOU",
				"choukoukouamine@gmail.com", "adresse", "06 00 00 00 00");
		aR.save(a);
		Collaborateur c = new Collaborateur("collaborateur1", passwordEncoder.encode("collaborateur1"), "TEST1",
				"COLLABORATEUR2", "choukoukouamine@outlook.com", "adresse", "06 00 00 00 00");
		cR.save(c);
		Encadrant e = new Encadrant("encadrant1", passwordEncoder.encode("encadrant1"), "YAHYA", "HARIF",
				"yahya.harif@gmail.com", "adresse", "06 00 00 00 00");
		eR.save(e);
		Manager m = new Manager("manager1", passwordEncoder.encode("manager1"), "TEST1", "MANAGER1",
				"yahya.harif@gmail.com", "adresse", "06 00 00 00 00");
		mR.save(m);
		Administrateur a2 = new Administrateur("admin2", passwordEncoder.encode("admin2"), "AMINE2", "CHOUKOUKOU2",
				"choukoukouamine@gmail.com", "adresse", "06 00 00 00 00");
		aR.save(a2);
		Collaborateur c2 = new Collaborateur("collaborateur2", passwordEncoder.encode("collaborateur2"), "TEST2",
				"COLLABORATEUR2", "choukoukouamine@outlook.com", "adresse", "06 00 00 00 00");
		cR.save(c2);
		Encadrant e2 = new Encadrant("encadrant2", passwordEncoder.encode("encadrant2"), "YAHYA2", "HARIF2",
				"yahya.harif@gmail.com", "adresse", "06 00 00 00 00");
		eR.save(e2);
		Manager m2 = new Manager("manager2", passwordEncoder.encode("manager2"), "TEST2", "MANAGER2",
				"yahya.harif@gmail.com", "adresse", "06 00 00 00 00");
		mR.save(m2);

		@SuppressWarnings("deprecation")
		BAP b1 = new BAP(new Date(116, 1, 1), c2, m2);
		br.save(b1);

		int i = 0;

		c.setProjets(new ArrayList<>());
		c2.setProjets(new ArrayList<>());
		while (i < 2) {
			Projet p = new Projet("projet " + i);
			if (i < 1) {
				p.setChefProjet(e);
			} else {
				p.setChefProjet(e2);
			}
			p.setCollaborateurs(new ArrayList<Collaborateur>());
			p.getCollaborateurs().add(c);
			projetRepository.save(p);
			c.getProjets().add(p);
			cR.save(c);
			p.getCollaborateurs().add(c2);
			projetRepository.save(p);
			c2.getProjets().add(p);
			cR.save(c2);
			i++;
		}
		i = 0;
		@SuppressWarnings("deprecation")
		BAP b = new BAP(new Date(115, 11, 1), c, a);
		b.setStatus(b.EN_COURS);
		b.setObjectifsEntrantes(new ArrayList<Objectif>());
		while (i < 2) {
			Objectif p = new Objectif("objectif" + i, "categorie");
			for (int j = 0; j < 2; j++) {
				Description d = new Description("description" + j, "mesure" + j, 50, 23);
				d.setResponsable(e);
				p.addDescription(d);
			}
			b.getObjectifsEntrantes().add(p);
			p.setEmploye(c);
			or.save(p);
			i++;
		}

		Objectif o = new Objectif("objectif", "categorie");
		for (int j = 0; j < 2; j++) {
			Description d = new Description("description" + j, "mesure" + j, 50, 23);
			d.setResponsable(e);
			o.addDescription(d);
		}
		or.save(o);
		b.addObjectifSortantes(o);
		br.save(b);

		System.out.println(
				"---------------------------------------------- Fin ----------------------------------------------");

	}

}

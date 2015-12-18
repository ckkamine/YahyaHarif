package com.lambda.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lambda.mail.MailComponent;
import com.lambda.repository.BapRepository;
import com.lambda.repository.EncadrantRepository;
import com.lambda.repository.ObjectifRepository;
import com.lambda.repository.ProjetRepository;
import com.lambda.entities.*;

@Controller
@EnableScheduling

public class MailService {

	@Autowired
	MailComponent mailComponent;

	@Autowired
	BapRepository br;
	
	@Autowired
	ProjetRepository projetRepository;

	@Autowired
	EncadrantRepository encadrantRepository;
	
	@Autowired
	BapRepository bapRepository;
	
	@Autowired
	ObjectifRepository objectifRepository;
	
	@RequestMapping("/user/bilan")
	//@Scheduled(cron = "0 0/1 * * * *") //1 minute
	//@Scheduled(cron = "0 0 5 1 1/1 *") 1 mois
	public void preparer() throws MessagingException {
		Calendar date= Calendar.getInstance();
		List<BAP> baps = br.findAll();
		Integer mois = date.get(Calendar.MONTH)+1;
		Integer annee= date.get(Calendar.YEAR);
		if(mois==12){
			mois=0;
			annee++;
		}
		for(BAP b: baps){
			System.out.println("BAP - "+b.getId()+" -- PREPARER");
			Collaborateur collaborateur= b.getCollaborateur();
			Calendar bapDate = Calendar.getInstance();
			bapDate.setTime(b.getDateBilan());
			if((bapDate.get(Calendar.MONTH)==mois)&&(bapDate.get(Calendar.YEAR)==annee)){
				b.setStatus(b.EN_COURS);
				// Transmettre les Objectifs
				List<Objectif> objectifsEntrantes= objectifRepository.getObjectifsCList(b.getCollaborateur().getMatricule());
				b.setObjectifsEntrantes(objectifsEntrantes);
				mailComponent.sendPreparer(b.getManager().getEmail(), b.getCollaborateur().getFirstName(), b.getCollaborateur().getLastName(), b.getCollaborateur().getPosteActuel(), b.getId(), b.getDateBilan());
				for(Projet p: b.getCollaborateur().getProjets()){
					System.out.println("--------------------------");
					System.out.println("Projet - "+p.getIdProjet());
					Encadrant e= p.getChefProjet();
					System.out.println(e.getCollaborateursCurrent().size()+" ===> "+e.getMatricule());
					if(!e.getCollaborateursCurrent().contains(collaborateur)){
						System.out.println("ACTION");
						e.getCollaborateursCurrent().add(collaborateur);
						encadrantRepository.save(e);
						mailComponent.sendFeedback(e.getEmail(), b.getCollaborateur().getFirstName(), b.getCollaborateur().getLastName(), b.getCollaborateur().getPosteActuel(), b.getId(), b.getDateBilan());
					
					}

					System.out.println("--------------------------");
					List<Collaborateur> listCol= encadrantRepository.findColloborateurCurrent(e.getMatricule());
					for(int i=0; listCol.size()>i;i++){
						try {
							if(listCol.get(i).equals(listCol.get(i+1))){
								listCol.remove(i);
							}
						} catch (Exception e1) {
						}
					}
					e.setCollaborateursCurrent(listCol);
					encadrantRepository.save(e);
					listCol= encadrantRepository.findColloborateurCurrent(e.getMatricule());
					for(int i=0; listCol.size()>i;i++){
						System.out.println(listCol.get(i).getMatricule()+"  -  "+listCol.get(i).getUsername());
					}
				}
			}
			bapRepository.save(b);
		}
		
		
	}

	

	//@Scheduled(cron = "0/10 * * * * *") 10 secondes
	//@Scheduled(cron = "0 0 5 1 1/1 *") 1 mois
	//@Scheduled(cron = "30 0/1 * * * *") //1 minute
	@RequestMapping("/user/bian2")
	public void realiser() throws MessagingException {
		Calendar date= Calendar.getInstance();
		List<BAP> baps = br.findAll();
		Integer mois = date.get(Calendar.MONTH);
		Integer annee= date.get(Calendar.YEAR);
		for(BAP b: baps){
			System.out.println("BAP - "+b.getId()+" -- REALISER");
			Calendar bapDate = Calendar.getInstance();
			bapDate.setTime(b.getDateBilan());
			if((bapDate.get(Calendar.MONTH)==mois)&&(bapDate.get(Calendar.YEAR)==annee)){
				mailComponent.sendRealiser(b.getManager().getEmail(), b.getCollaborateur().getFirstName(), b.getCollaborateur().getLastName(), b.getCollaborateur().getPosteActuel(), b.getId(), b.getDateBilan());
				System.out.println("Mail===> "+b.getManager().getUsername());
			}
		}
				
	}

	
}
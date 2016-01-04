package com.lambda.metier;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lambda.entities.BAP;
import com.lambda.entities.Collaborateur;
import com.lambda.entities.Feedback;
import com.lambda.entities.Projet;
import com.lambda.entities.Qualification;
import com.lambda.mail.MailComponent;
import com.lambda.repository.BapRepository;
import com.lambda.repository.EncadrantRepository;
import com.lambda.repository.FeedbackRepository;
import com.lambda.repository.ProjetRepository;
import com.lambda.repository.QualificationRepository;

@Service
@Transactional
public class EncadrantMetierImpl implements EncadrantMetier {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private ProjetRepository projetRepository;

	@Autowired
	private QualificationRepository qualificationRepository;

	@Autowired
	BapRepository bapRepository;

	@Autowired
	MailComponent mailCoponent;

	@Autowired
	EncadrantRepository encadrantRepository;

	@Override
	public Page<Feedback> getFeedbacks(Long matricule, Integer page) {
		return feedbackRepository.getFeedbacks(matricule, new PageRequest(page, 6));
	}

	@Override
	public List<Projet> getProjets(Long matricule) {
		return projetRepository.getProjetsByChef(matricule);
	}

	@Override
	public Feedback addFeedback(Feedback feedback) {
		for (Qualification q : feedback.getQualifications()) {
			qualificationRepository.save(q);
		}

		BAP bap = bapRepository.findByCollaborateur(feedback.getCollaborateur().getMatricule());
		if (bap.isLocked()) {
			feedback.setLocked(true);
		} else {
			feedback.setLocked(false);
		}
		feedbackRepository.save(feedback);
		bap.addFeedback(feedback);
		bapRepository.save(bap);
		try {
			mailCoponent.sendNvFeedback(bap.getManager().getEmail(), feedback.getCollaborateur().getFirstName(),
					feedback.getCollaborateur().getLastName(), feedback.getCollaborateur().getPosteActuel(),
					bap.getId(), new Date());
			mailCoponent.sendNvFeedback(bap.getCollaborateur().getEmail(), feedback.getCollaborateur().getFirstName(),
					feedback.getCollaborateur().getLastName(), feedback.getCollaborateur().getPosteActuel(),
					bap.getId(), new Date());
		} catch (MessagingException e) {

		}
		return feedback;
	}

	@Override
	public List<Collaborateur> getCollaborateursCurrent(Long matricule) {
		return encadrantRepository.findColloborateurCurrent(matricule);
	}

	@Override
	public void retirerFeedback(Long matricule, Long idFeedback) {
		Feedback feedback = null;
		List<Feedback> listFeedback = feedbackRepository.getFeedbacksList(matricule);
		for (Feedback f : listFeedback) {
			if (f.getIdFeedback() == idFeedback) {
				feedback = f;
			}
		}
		try {
			BAP bap = bapRepository.findByCollaborateur(feedback.getCollaborateur().getMatricule());
			bap.getFeedbacks().remove(feedback);

		} catch (Exception e) {
			System.out.println("BAP non trouvé");
		}

		feedbackRepository.delete(feedback);

	}

	@Override
	public Page<Feedback> getFeedbacksA(Long matricule, Integer page) {
		return feedbackRepository.getFeedbacksA(matricule, new PageRequest(page, 10));
	}

	@Override
	public Integer getNombreFeedback(Long matricule) {
		return encadrantRepository.nombreFeedback(matricule);
	}

}

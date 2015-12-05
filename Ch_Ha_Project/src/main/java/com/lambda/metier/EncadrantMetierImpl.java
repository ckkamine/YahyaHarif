package com.lambda.metier;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lambda.entities.Feedback;
import com.lambda.entities.Projet;
import com.lambda.repository.FeedbackRepository;
import com.lambda.repository.ProjetRepository;
@Service
@Transactional
public class EncadrantMetierImpl implements EncadrantMetier{

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private ProjetRepository projetRepository;
	
	@Override
	public Page<Feedback> getFeedbacks(Long matricule, Integer page) {
		return feedbackRepository.getFeedbacks(matricule, new PageRequest(page, 6));
	}

	@Override
	public Feedback addFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

	@Override
	public List<Projet> getProjets(Long matricule) {
		// TODO Auto-generated method stub
		return projetRepository.getProjetsByChef(matricule);
	}

	



}

package com.lambda.metier;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lambda.entities.Feedback;

public interface EncadrantMetier {
	
	public Page<Feedback> getFeedbacks(Long matricule, Integer page);
	public Feedback addFeedback(Feedback feedback);
}

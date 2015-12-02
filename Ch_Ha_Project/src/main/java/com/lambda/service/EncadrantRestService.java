package com.lambda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.entities.Feedback;
import com.lambda.metier.EncadrantMetier;

@RestController
@RequestMapping("/rest")
public class EncadrantRestService {

	@Autowired
	EncadrantMetier encadrantMetier;

	@RequestMapping(value="/encadrant", method= RequestMethod.GET)
	public Page<Feedback> getFeedbacks(@RequestParam(value="com") Long matricule, 
			@RequestParam(value = "page") Integer page) {
		return encadrantMetier.getFeedbacks(matricule, page);
	}

	@RequestMapping(value="/encadrant", method= RequestMethod.POST)
	public Feedback addFeedback(@RequestBody Feedback feedback) {
		return encadrantMetier.addFeedback(feedback);
	}
	
	
}
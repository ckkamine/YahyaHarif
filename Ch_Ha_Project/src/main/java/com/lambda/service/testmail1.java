package com.lambda.service;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lambda.mail.MailComponent;

@Controller
@RequestMapping("/user")
public class testmail1 {

	@Autowired
	MailComponent mailComponent;
	
	Date date = new Date();
	
	
	
	@RequestMapping("/preparer")
	public void preparer() throws MessagingException{
		mailComponent.sendPreparer("choukoukouamine@gmail.com", "amine", "ckk", "test", 1L, date);
	}
	
	@RequestMapping("/nvfeedback")
	public void nouveaufeedback() throws MessagingException{
		mailComponent.sendNvFeedback("choukoukouamine@gmail.com", "amine", "ckk", "test", 1L, date);
	}
	
	@RequestMapping("/feedback")
	public void feedback() throws MessagingException{
		mailComponent.sendFeedback("choukoukouamine@gmail.com", "amine", "ckk", "test", 1L, date);
	}
	@RequestMapping("/realiser")
	public void realiser() throws MessagingException{
		mailComponent.sendRealiser("choukoukouamine@gmail.com", "amine", "ckk", "test", 1L, date);
	}
	@RequestMapping("/mdp")
	public void mdp() throws MessagingException{
		mailComponent.sendUserpass("choukoukouamine@gmail.com", "amine", "ckk", "test", "test");
	}
}
package com.lambda.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lambda.mail.MailComponent;

@Controller
@RequestMapping("/user")
public class testmail {

	@Autowired
	MailComponent mailComponent;
	
	@RequestMapping("/preparer")
	public void preparer() throws MessagingException{
		mailComponent.sendPreparer("choukoukouamine@gmail.com", "amine", "ckk", "test", 1L);
	}
	
	@RequestMapping("/feedback")
	public void feedback() throws MessagingException{
		mailComponent.sendFeedback("choukoukouamine@gmail.com", "amine", "ckk", "test", 1L);
	}
	@RequestMapping("/realiser")
	public void realiser() throws MessagingException{
		mailComponent.sendRealiser("choukoukouamine@gmail.com", "amine", "ckk", "test", 1L);
	}
	@RequestMapping("/mdp")
	public void mdp() throws MessagingException{
		mailComponent.sendUserpass("choukoukouamine@gmail.com", "amine", "ckk", "test", "test");
	}
}

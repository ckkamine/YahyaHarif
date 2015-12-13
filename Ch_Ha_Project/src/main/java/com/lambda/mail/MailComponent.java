package com.lambda.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class MailComponent {
	@Autowired
	private JavaMailSender javaMailSender;
	
    @Autowired
    private SpringTemplateEngine templateEngine;

	public void sendPreparer(String email, String nom, String prenom, String posteActuel, Long id) throws MessagingException {
			
		final Context ctx = new Context();
		ctx.setVariable("nom", nom);
		ctx.setVariable("prenom", prenom);
		ctx.setVariable("posteActuel", posteActuel);
		ctx.setVariable("id", id);
		final String htmlContent = templateEngine.process("preparer", ctx);

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true);
		helper.setSubject("Vous avez un BAP à préparer.");
		helper.setTo(email);
		helper.setText(htmlContent, true);

		javaMailSender.send(message);

	}
	
	public void sendRealiser(String email, String nom, String prenom, String posteActuel, Long id) throws MessagingException {
		
		final Context ctx = new Context();
		ctx.setVariable("nom", nom);
		ctx.setVariable("prenom", prenom);
		ctx.setVariable("posteActuel", posteActuel);
		ctx.setVariable("id", id);
		final String htmlContent = templateEngine.process("realiser", ctx);

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true);
		helper.setSubject("Vous avez un BAP à réaliser.");
		helper.setTo(email);
		helper.setText(htmlContent, true);

		javaMailSender.send(message);

	}
	
	public void sendFeedback(String email, String nom, String prenom, String posteActuel, Long id) throws MessagingException {
		
		final Context ctx = new Context();
		ctx.setVariable("nom", nom);
		ctx.setVariable("prenom", prenom);
		ctx.setVariable("posteActuel", posteActuel);
		ctx.setVariable("id", id);
		final String htmlContent = templateEngine.process("feedback", ctx);

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true);
		helper.setSubject("Vous avez une fiche d'évaluation à rendre.");
		helper.setTo(email);
		helper.setText(htmlContent, true);

		javaMailSender.send(message);

	}
	
public void sendUserpass(String email, String nom, String prenom, String username, String password) throws MessagingException {
		
		final Context ctx = new Context();
		ctx.setVariable("nom", nom);
		ctx.setVariable("prenom", prenom);
		ctx.setVariable("username", username);
		ctx.setVariable("password", password);
		final String htmlContent = templateEngine.process("userpass", ctx);

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true);
		helper.setSubject("Vos identifiants LAMBDA.");
		helper.setTo(email);
		helper.setText(htmlContent, true);

		javaMailSender.send(message);

	}

}

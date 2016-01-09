package com.lambda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lambda.entities.User;
import com.lambda.metier.UserMetier;

@Component
public class NotreUserDetailsService implements UserDetailsService {

	private UserMetier userMetier;

	@Autowired
	public NotreUserDetailsService(UserMetier repository) {
		this.userMetier = repository;
	}

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userMetier.findByUsername(username);

		if (null == user) {
			throw new UsernameNotFoundException("L'utilisateur avec l'username " + username + " est introuvable");
		}
		return user;
	}

}

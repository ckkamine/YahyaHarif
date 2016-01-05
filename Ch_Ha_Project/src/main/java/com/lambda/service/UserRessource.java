package com.lambda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.entities.User;
import com.lambda.metier.UserMetier;
import com.lambda.security.TokenUtils;

@RestController
public class UserRessource {

	@Autowired
	UserMetier userMetier;

	@Autowired
	UserDetailsService userService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authManager;

	@RequestMapping(value = "/rest/updatepassword", method = RequestMethod.POST)
	public User updatePassword(@RequestParam String password, @RequestParam Long matricule) {
		return userMetier.updatePassword(matricule, password);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/user/authenticate", method = RequestMethod.POST)
	public User authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		User userDetails = (User) this.userService.loadUserByUsername(username);

		userDetails.setToken(tokenUtils.createToken(userDetails));

		return userDetails;
	}

}

package com.lambda.metier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lambda.entities.User;
import com.lambda.repository.UserRepository;

@Service
@Transactional
public class UserMetierImpl implements UserMetier {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User updatePassword(Long matricule, String password) {
		User user = userRepository.findOne(matricule);
		user.setPassword(passwordEncoder.encode(password));
		return user;
	}

	@Override
	public User findByUsername(String name) {
		User user = userRepository.findByUsername(name);
		return user;
	}

}

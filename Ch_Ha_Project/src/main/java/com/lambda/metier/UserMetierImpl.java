package com.lambda.metier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lambda.entities.User;
import com.lambda.repository.UserRepository;

@Service
@Transactional
public class UserMetierImpl implements UserMetier{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User updatePassword(Long matricule, String password) {
		User user= userRepository.findOne(matricule);
		user.setPassword(password);
		return user;
	}
	
	@Override
	public User findByUsername(String name) {
		User user= userRepository.findByUsername(name);
		return user;
	}

}

package com.lambda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambda.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("select u from User u")
	Page<User> getAllUsers(Pageable page);
	
	@Query("select u from User u where u.firstName like :x or "
			+ "u.lastName like :x or u.email like :x or u.telephone like :x or u.adresse like :x or u.posteActuel like :x or u.username like :x")
	Page<User> findByMcUsers(@Param("x") String mc, Pageable page);
	
	@Query("select u.username from User u")
	List<String> getAllUsername();
	
	User findByUsername(String username);
}

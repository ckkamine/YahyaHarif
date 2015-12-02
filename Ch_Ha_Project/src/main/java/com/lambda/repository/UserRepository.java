package com.lambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lambda.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

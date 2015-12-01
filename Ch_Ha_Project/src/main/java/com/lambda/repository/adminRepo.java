package com.lambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lambda.entities.Administrateur;

public interface adminRepo extends JpaRepository<Administrateur, Long>{

}

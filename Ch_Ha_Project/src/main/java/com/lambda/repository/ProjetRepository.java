package com.lambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lambda.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long>{

}

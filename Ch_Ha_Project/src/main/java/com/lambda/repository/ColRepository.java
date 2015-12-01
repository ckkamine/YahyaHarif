package com.lambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lambda.entities.Collaborateur;

@Repository
public interface ColRepository extends JpaRepository<Collaborateur, Long>{

}

package com.lambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lambda.entities.Encadrant;

public interface EncadrantRepository extends JpaRepository<Encadrant, Long> {

}

package com.lambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lambda.entities.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {

}

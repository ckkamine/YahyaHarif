package com.lambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lambda.entities.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}

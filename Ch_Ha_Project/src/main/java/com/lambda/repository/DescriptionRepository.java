package com.lambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lambda.entities.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long> {

}

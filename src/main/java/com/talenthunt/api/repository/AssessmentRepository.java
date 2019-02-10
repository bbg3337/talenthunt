package com.talenthunt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Assesment;

/**
 * The interface User repository.
 *
 * @author Harmohan
 */
@Repository
public interface AssessmentRepository extends JpaRepository<Assesment, Long> {}

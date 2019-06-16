package com.talenthunt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Assesment;
import com.talenthunt.api.model.AssesmentDetails;

/**
 * The interface User repository.
 *
 * @author Harmohan
 */
@Repository
public interface AssessmentDetailsRepository extends JpaRepository<AssesmentDetails, Long> {}

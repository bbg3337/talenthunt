package com.talenthunt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.AssessmentPriceGrid;

/**
 * The interface User repository.
 *
 * @author Bhadresh Bajariya
 */

@Repository
public interface AssessmentPriceGridRepository extends JpaRepository<AssessmentPriceGrid, Long> {

}

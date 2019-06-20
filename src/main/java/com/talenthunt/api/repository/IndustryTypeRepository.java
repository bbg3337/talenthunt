package com.talenthunt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.IndustryType;

/**
 * The interface User repository.
 *
 * @author Bhadresh Bajariya
 */
@Repository
public interface IndustryTypeRepository extends JpaRepository<IndustryType, Long> {}

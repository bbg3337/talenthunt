package com.talenthunt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.QuestionType;

/**
 * The interface Question Type repository.
 *
 * @author Harmohan
 */
@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {}

package com.talenthunt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Subject;
import com.talenthunt.api.model.User;

/**
 * The interface User repository.
 *
 * @author Harmohan
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {}

package com.talenthunt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Category;

/**
 * The interface Category repository.
 *
 * @author Harmohan
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}

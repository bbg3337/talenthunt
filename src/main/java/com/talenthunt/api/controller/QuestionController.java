package com.talenthunt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.model.Category;
import com.talenthunt.api.model.User;
import com.talenthunt.api.repository.CategoryRepository;
import com.talenthunt.api.repository.SubjectRepository;

/**
 * Controller for question
 * for the time being category and topic are here if require we will move them 
 * to different controller
 * @author Harmohan
 *
 */

@RestController
@RequestMapping("/api/v1")
public class QuestionController 
{
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SubjectRepository subjectRepository;
	
	
	/**
	 * Get all category
	 * @return list category
	 */
	@GetMapping("/category")
	  public List<Category> getAllCategory() {
	    return categoryRepository.findAll();
	  }
	
	
	/**
	 * Get all subject
	 * @return list subject
	 */
	@GetMapping("/subject")
	  public List<User> getAllSubject() {
	    return subjectRepository.findAll();
	  }

}

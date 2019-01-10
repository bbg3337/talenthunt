package com.talenthunt.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Category;
import com.talenthunt.api.model.Question;
import com.talenthunt.api.model.User;
import com.talenthunt.api.repository.CategoryRepository;
import com.talenthunt.api.repository.QuestionRepository;
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
	@Autowired
	QuestionRepository questionRepository; 
	
	
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

	
	/**
	   * Get all Question list.
	   *
	   * @return the list
	   */
	  @GetMapping("/questions")
	  public List<Question> getAllQuestions() {
	    return questionRepository.findAll();
	  }

	  /**
	   * Gets question by id.
	   *
	   * @param queId the question id
	   * @return the question by id
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/questions/{id}")
	  public ResponseEntity<Question> getQuestionById(@PathVariable(value = "id") Long queId)
	      throws ResourceNotFoundException {
		  Question question =
	    		questionRepository
	            .findById(queId)
	            .orElseThrow(() -> new ResourceNotFoundException("Question not found on :: " + queId));
	    return ResponseEntity.ok().body(question);
	  }

	  /**
	   * Create Question Question.
	   *
	   * @param Question 
	   * @return the Question
	   */
	  @PostMapping("/question")
	  public Question createQuestion(@Valid @RequestBody Question question) {
	    return questionRepository.save(question);
	  }

}

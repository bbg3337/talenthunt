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

import com.talenthunt.api.bo.QuestionBo;
import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Category;
import com.talenthunt.api.model.QuestionSubjective;
import com.talenthunt.api.model.Subject;
import com.talenthunt.api.model.Topic;
import com.talenthunt.api.repository.SubjectiveRepository;

/**
 * Controller for question
 * for the time being category and topic are here if require we will move them 
 * to different controller
 * @author Harmohan
 *
 */

@RestController
@RequestMapping("/api/v1")
public class SubjectiveController 
{

	@Autowired
	SubjectiveRepository subjectiveRepository;  
	
	/**
	   * Get all Question list.
	   *
	   * @return the list
	   */
	  @GetMapping("/subjective")
	  public List<QuestionSubjective> getAllQuestions() {
	    return subjectiveRepository.findAll();
	  }

	  /**
	   * Gets question by id.
	   *
	   * @param queId the question id
	   * @return the question by id
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/subjective/{id}")
	  public ResponseEntity<QuestionSubjective> getQuestionById(@PathVariable(value = "id") Long queId)
	      throws ResourceNotFoundException {
		  QuestionSubjective question =
				  subjectiveRepository
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
	  @PostMapping("/subjective")
	  public QuestionSubjective createQuestion(@Valid @RequestBody QuestionBo questionBo) 
	  {
		  QuestionSubjective question=new QuestionSubjective();
		  Subject subject=new Subject();
		  Topic topic=new Topic();
		  Category category=new Category();
		  subject.setId(questionBo.getSubjectId());
		  topic.setId(questionBo.getTopicId());
		  category.setId(questionBo.getCategoryId());
		  question.setAnswer(questionBo.getAnswer());
		  question.setHint(questionBo.getHint());
		  question.setQuestion(questionBo.getQuestion());
		  question.setSubject(subject);
		  question.setCategory(category);
		  question.setTopic(topic);
		  return subjectiveRepository.save(question);
	  }
	  
	  
}

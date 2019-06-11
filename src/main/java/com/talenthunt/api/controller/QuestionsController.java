package com.talenthunt.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Questions;
import com.talenthunt.api.repository.QuestionsRepository;

@RestController
@RequestMapping("/api/v1/questions")
@CrossOrigin
public class QuestionsController {
	@Autowired
	private QuestionsRepository questionsRepository;

	@PostMapping("/create")
	public Questions createQuestion(@Valid @RequestBody Questions questions) {
		// questions.setCreatedAt(new Date());
		System.out.println("QuestionsController.createQuestion()1");
		return questionsRepository.save(questions);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Questions> getQuestionById(@PathVariable(value = "id") Long questionId)
			throws ResourceNotFoundException {
		Questions questions = questionsRepository.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException("Question not found on :: " + questionId));
		return ResponseEntity.ok().body(questions);
	}
	
	@GetMapping("/get/{categoryId}/{subjectId}/{topicId}/{languageId}")
	public List<Questions> getByQuestionType(@PathVariable(value = "categoryId") Long categoryId,
			@PathVariable(value = "subjectId") Long subjectId,
			@PathVariable(value = "topicId") Long topicId,
			@PathVariable(value = "languageId") Long languageId)
			throws ResourceNotFoundException {
		List<Questions> questions = questionsRepository.getByQuestionType(categoryId,subjectId,topicId,languageId);
		return questions;
	}
	
	@PostMapping("/update")
	public Questions updateQuestion(@Valid @RequestBody Questions questions) {
		// questions.setCreatedAt(new Date());
		Questions updateQuestions = questionsRepository.getOne(questions.getId());
	/*	updateQuestions.setQuestionHintText(questions.getQuestionHintText());
		updateQuestions.setAnswerExplanation(questions.getQuestionHintText());*/
		updateQuestions.setQuestionText(questions.getQuestionText());
		return questionsRepository.save(updateQuestions);
	}
	@PostMapping("/delete/{id}")
	public ResponseEntity<String> updateQuestion(@PathVariable(value = "id") Long questionId) {
		try {
			questionsRepository.deleteById(questionId);
			return  ResponseEntity.ok("Delete Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("Delete Failed");
		} 
				
	}
}

package com.talenthunt.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Subject;
import com.talenthunt.api.repository.SubjectRepository;

@RestController
@RequestMapping("/api/v1/subject")
@CrossOrigin
public class SubjectController {

	@Autowired
	private SubjectRepository subjectRepository;

	@PostMapping("/create")
	public Subject createSubject(@Valid @RequestBody Subject subject) {
		return subjectRepository.save(subject);
	}

	@GetMapping("/")
	public List<Subject> getAllSubject() {
		return subjectRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Subject> getSubjectById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Subject subject = subjectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Question not found on :: " + id));
		return ResponseEntity.ok().body(subject);
	}
	
	@PostMapping("/update")
	public Subject updateSubject(@Valid @RequestBody Subject subject) {
		Subject sub = subjectRepository.getOne(subject.getSubjectId());
		sub.setSubjectName(subject.getSubjectName());
		return subjectRepository.save(sub);
	}
}

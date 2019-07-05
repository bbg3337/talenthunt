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
import com.talenthunt.api.model.PurposeAssessmet;
import com.talenthunt.api.repository.PurposeAssessmetRepository;



@RestController
@RequestMapping("/api/v1/purposeAssessmet")
@CrossOrigin
public class PurposeAssessmetController {
	@Autowired
	private PurposeAssessmetRepository purposeAssessmetRepository;

	@PostMapping("/create")
	public PurposeAssessmet createPurposeAssessmet(@Valid @RequestBody PurposeAssessmet PurposeAssessmet) {
		return purposeAssessmetRepository.save(PurposeAssessmet);
	}

	@GetMapping("/")
	public List<PurposeAssessmet> getAllPurposeAssessmet() {
		return purposeAssessmetRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<PurposeAssessmet> getPurposeAssessmetById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		PurposeAssessmet PurposeAssessmet = purposeAssessmetRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Industry Type not found on :: " + id));
		return ResponseEntity.ok().body(PurposeAssessmet);
	}
	
	@PostMapping("/update")
	public PurposeAssessmet updatePurposeAssessmet(@Valid @RequestBody PurposeAssessmet PurposeAssessmet) {
		PurposeAssessmet sub = purposeAssessmetRepository.getOne(PurposeAssessmet.getId());
		sub.setPurposeAssessmetText(PurposeAssessmet.getPurposeAssessmetText());
		return purposeAssessmetRepository.save(sub);
	}
}

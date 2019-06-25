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
import com.talenthunt.api.model.IndustryType;
import com.talenthunt.api.repository.IndustryTypeRepository;


@RestController
@RequestMapping("/api/v1/industryType")
@CrossOrigin
public class IndustryTypeController {

	@Autowired
	private IndustryTypeRepository industryTypeRepository;

	@PostMapping("/create")
	public IndustryType createIndustryType(@Valid @RequestBody IndustryType IndustryType) {
		return industryTypeRepository.save(IndustryType);
	}

	@GetMapping("/")
	public List<IndustryType> getAllIndustryType() {
		return industryTypeRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<IndustryType> getIndustryTypeById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		IndustryType IndustryType = industryTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Industry Type not found on :: " + id));
		return ResponseEntity.ok().body(IndustryType);
	}
	
	@PostMapping("/update")
	public IndustryType updateIndustryType(@Valid @RequestBody IndustryType IndustryType) {
		IndustryType sub = industryTypeRepository.getOne(IndustryType.getId());
		sub.setIndustryType(IndustryType.getIndustryType());
		return industryTypeRepository.save(sub);
	}
}

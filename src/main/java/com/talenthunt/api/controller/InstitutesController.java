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
import com.talenthunt.api.model.Institutes;
import com.talenthunt.api.repository.InstitutesRepository;

@RestController
@RequestMapping("/api/v1/institutes")
@CrossOrigin
public class InstitutesController {
	@Autowired
	private InstitutesRepository institutesRepository;

	@PostMapping("/create")
	public Institutes createInstitutes(@Valid @RequestBody Institutes institutes) {
		return institutesRepository.save(institutes);
	}

	@GetMapping("/")
	public List<Institutes> getAllInstitutes() {
		return institutesRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Institutes> getInstitutesById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Institutes institutes = institutesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Institutes not found on :: " + id));
		return ResponseEntity.ok().body(institutes);
	}

	@PostMapping("/update")
	public Institutes updateAssessmentPriceGrid(@Valid @RequestBody Institutes institutes) {
		Institutes inst = institutesRepository.getOne(institutes.getInstituteId());
		inst.setContactPersEmail(institutes.getContactPersEmail());
		inst.setInstituteCity(institutes.getInstituteCity());
		inst.setInstituteDesc(institutes.getInstituteDesc());
		inst.setInstituteEmail(institutes.getInstituteEmail());
		inst.setInstituteImage(institutes.getInstituteImage());
		inst.setInstituteLogoLoc(institutes.getInstituteLogoLoc());
		inst.setInstituteLogoName(institutes.getInstituteLogoName());
		inst.setInstituteName(institutes.getInstituteName());
		inst.setInstituteStateCd(institutes.getInstituteStateCd());
		inst.setInstituteWeb(institutes.getInstituteWeb());
		return institutesRepository.save(inst);
	}
}

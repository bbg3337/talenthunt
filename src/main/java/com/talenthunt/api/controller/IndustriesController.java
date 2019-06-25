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
import com.talenthunt.api.model.Industries;
import com.talenthunt.api.repository.IndustriesRepository;

@RestController
@RequestMapping("/api/v1/industries")
@CrossOrigin
public class IndustriesController {

	@Autowired
	private IndustriesRepository industriesRepository;

	@PostMapping("/create")
	public Industries createIndustries(@Valid @RequestBody Industries industries) {
		return industriesRepository.save(industries);
	}

	@GetMapping("/")
	public List<Industries> getAllIndustries() {
		return industriesRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Industries> getIndustriesById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Industries industries = industriesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Question not found on :: " + id));
		return ResponseEntity.ok().body(industries);
	}
	
	@PostMapping("/update")
	public Industries updateIndustries(@Valid @RequestBody Industries industries) {
		Industries industry= industriesRepository.getOne(industries.getIndustryId());
		industry.setIndustryName(industries.getIndustryName());
		industry.setIndustryDesc(industries.getIndustryDesc());
		return industriesRepository.save(industry);
	}
	
	@DeleteMapping("/{id}")
	public Industries deleteIndustries(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		Industries industry = industriesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Industries not found on :: " + id));
		industriesRepository.delete(industry);
		return industry;
	}
	
}

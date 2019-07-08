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
import com.talenthunt.api.model.CompanyRole;
import com.talenthunt.api.repository.CompanyRoleRepository;


@RestController
@RequestMapping("/api/v1/companyRole")
@CrossOrigin
public class CompanyRoleController {

	@Autowired
	private CompanyRoleRepository companyRoleRepository;

	@PostMapping("/create")
	public CompanyRole createCompanyRole(@Valid @RequestBody CompanyRole companyRole) {
		return companyRoleRepository.save(companyRole);
	}

	@GetMapping("/")
	public List<CompanyRole> getAllCompanyRole() {
		return companyRoleRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<CompanyRole> getCompanyRoleById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		CompanyRole companyRole = companyRoleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company Role not found on :: " + id));
		return ResponseEntity.ok().body(companyRole);
	}
	
	@PostMapping("/update")
	public CompanyRole updateIndustryType(@Valid @RequestBody CompanyRole companyRole) {
		CompanyRole sub = companyRoleRepository.getOne(companyRole.getId());
		sub.setName(companyRole.getName());
		return companyRoleRepository.save(sub);
	}
}

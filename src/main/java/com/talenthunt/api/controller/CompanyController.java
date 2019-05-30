package com.talenthunt.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.model.Company;
import com.talenthunt.api.repository.CompanyRepository;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {

	@Autowired
	CompanyRepository companyRepository;
	
	@PostMapping("/company")
	public Company createCompany(@Valid @RequestBody Company company) {
		return companyRepository.save(company);
	}
}

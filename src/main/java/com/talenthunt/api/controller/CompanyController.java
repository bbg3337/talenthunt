package com.talenthunt.api.controller;

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
import com.talenthunt.api.model.Company;
import com.talenthunt.api.model.Questions;
import com.talenthunt.api.repository.CompanyRepository;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

	@Autowired
	CompanyRepository companyRepository;
	
	@PostMapping("/add")
	public Company createCompany(@Valid @RequestBody Company company) {
		return companyRepository.save(company);
	}
	
	/*@GetMapping("/get/{id}")
	public Company getCompanyById(@PathVariable(value = "id") Long companyId) {
		return companyRepository.getOne(companyId);
	}*/
	@GetMapping("/get/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") Long companyId)
			throws ResourceNotFoundException {
		Company company= companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Question not found on :: " + companyId));
		return ResponseEntity.ok().body(company);
	}
	
	@PostMapping("/update")
	public Company updateCompany(@Valid @RequestBody Company company) {
		Company updateCompany = companyRepository.getOne(company.getId());
		updateCompany.setCity(company.getCity());
		updateCompany.setCompanyName(company.getCompanyName());
		updateCompany.setCompanyAddress(company.getCompanyAddress());
		updateCompany.setCompanyEmail(company.getCompanyEmail());
		updateCompany.setIndustryType(company.getIndustryType());
		updateCompany.setPhoneNo(company.getPhoneNo());
		updateCompany.setState(company.getState());
		return companyRepository.save(updateCompany);
	}
}

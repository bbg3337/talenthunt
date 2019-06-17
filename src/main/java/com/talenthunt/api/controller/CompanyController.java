package com.talenthunt.api.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talenthunt.api.enums.UserType;
import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Company;
import com.talenthunt.api.model.CompanyCandidates;
import com.talenthunt.api.model.User;
import com.talenthunt.api.repository.CompanyCandidatesRepository;
import com.talenthunt.api.repository.CompanyRepository;
import com.talenthunt.api.repository.UserRepository;
import com.talenthunt.api.service.CommonService;

@RestController
@RequestMapping("/api/v1/company")
@CrossOrigin
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private CompanyCandidatesRepository companyCandidatesRepository; 
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private CommonService commonService;
	
	@PostMapping("/add")
	public Company createCompany(@Valid @RequestBody Company company) {
	  	ObjectMapper mapperObj = new ObjectMapper();
	  	try {
			company.setContactPerson(mapperObj.writeValueAsString(company.getContactPersons()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	  	User user = new User();
	  	user.setEmail(company.getCompanyEmail());
	  	user.setFirstName(company.getCompanyName());
	  	user.setLastName(company.getCompanyName());
	  	user.setUserType(UserType.Admin);
	  	user.setUserName(company.getCompanyEmail());
	  	user.setPassword(CommonService.generatePassword(10));
	  	userRepository.save(user);
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
		ObjectMapper mapperObj = new ObjectMapper();
	  	try {
			updateCompany.setContactPerson(mapperObj.writeValueAsString(company.getContactPersons()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		updateCompany.setCity(company.getCity());
		updateCompany.setCompanyName(company.getCompanyName());
		updateCompany.setCompanyAddress(company.getCompanyAddress());
		updateCompany.setCompanyEmail(company.getCompanyEmail());
		updateCompany.setIndustryType(company.getIndustryType());
		updateCompany.setPhoneNo(company.getPhoneNo());
		updateCompany.setState(company.getState());
		return companyRepository.save(updateCompany);
	}
	
	@GetMapping("/")
	public List<Company> getCompanyList()
			throws ResourceNotFoundException {
		return companyRepository.findAll();
	}
	
	@PostMapping("/candidates/create")
	public CompanyCandidates createCandidates(@Valid @RequestBody CompanyCandidates companyCandidates) throws Exception {
		companyCandidates.setAccessCode(commonService.generatePassword(16));
		System.out.println("CompanyController.createCandidates()" + companyCandidates.getAccessCode());
		companyCandidates.setAccessCodeCreatedDate(new Date());
		CompanyCandidates  candidates = companyCandidatesRepository.save(companyCandidates);
		if(candidates != null)
		{
			try {
				commonService.sendEmail(candidates.getCandidateEmailId(), "Access Your Assignment", candidates.getAccessCode());	
			}catch(MailAuthenticationException e){
				throw new Exception("Record save but email not send due to Username and password are invelid");
			}catch (Exception e) {
				throw e;
			}
	        
		}
		return candidates;
	}
	
	@GetMapping("/candidates/{companyId}")
	public List<CompanyCandidates> getCandidates(@PathVariable(value = "companyId") Long companyId) {
		List<CompanyCandidates>  candidates = companyCandidatesRepository.getCandidateByCompanyId(companyId);
		return candidates;
	}
}

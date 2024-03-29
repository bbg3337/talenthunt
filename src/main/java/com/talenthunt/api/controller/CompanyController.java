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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.talenthunt.api.bo.ContactPerson;
import com.talenthunt.api.bo.Message;
import com.talenthunt.api.enums.TestStatus;
import com.talenthunt.api.enums.UserType;
import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Assesment;
import com.talenthunt.api.model.Company;
import com.talenthunt.api.model.CompanyCandidates;
import com.talenthunt.api.model.User;
import com.talenthunt.api.model.UserScoreboard;
import com.talenthunt.api.repository.AssessmentRepository;
import com.talenthunt.api.repository.CompanyCandidatesRepository;
import com.talenthunt.api.repository.CompanyRepository;
import com.talenthunt.api.repository.UserRepository;
import com.talenthunt.api.repository.UserScoreboardRepository;
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
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	@Autowired
	private UserScoreboardRepository userScoreboardRepository;
	
	@PostMapping("/add")
	public Company createCompany(@Valid @RequestBody Company company) throws Exception {
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
	  	user=userRepository.save(user);
		if(user != null){
	  		try {
				commonService.sendEmail(user.getEmail(), "Access Your Compnay Login", user.getPassword());	
			}catch(MailAuthenticationException e){
				throw new Exception("Record save but email not send due to Username and password are invelid");
			}catch (Exception e) {
				throw e;
			}
	  	}
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
				commonService.sendEmail(candidates.getCandidateEmailId(), "Access Your Accounts", candidates.getAccessCode());	
			}catch(MailAuthenticationException e){
				throw new Exception("Record save but email not send due to Username and password are invelid");
			}catch (Exception e) {
				throw e;
			}
		}
	/*	UserScoreboard userScoreboard =  new UserScoreboard();
		userScoreboard.setTestStatus(TestStatus.InComplete);
		
		//How to get Test type?
		//userScoreboard.setTestType(assesments.);
		//How to get topic id, it is not define in the assessment table and any other related table?
		//userScoreboard.setTopicId(companyCandidates.get);
		userScoreboard.setUserSeq(candidates.getId());
		userScoreboard.setCompanyId(candidates.getCompanyId());
		userScoreboardRepository.save(userScoreboard);*/
		return candidates;
	}
	
	@GetMapping("/candidates/{companyId}")
	public List<CompanyCandidates> getCandidates(@PathVariable(value = "companyId") Long companyId) {
		List<CompanyCandidates>  candidates = companyCandidatesRepository.getCandidateByCompanyId(companyId);
		return candidates;
	}
	
	@PostMapping("/candidates/assignAssessment")
	public UserScoreboard assignAssessment(@RequestBody ObjectNode objectNode) throws Exception{
		Long candidateId = objectNode.get("candidateId").asLong();
		Long assessmentId = objectNode.get("assessmentId").asLong();
		Long companyId = objectNode.get("companyId").asLong();
		UserScoreboard userScoreboard =  new UserScoreboard();
		userScoreboard.setTestStatus(TestStatus.InComplete);
		
		//How to get Test type?
		//userScoreboard.setTestType(assesments.);
		//How to get topic id, it is not define in the assessment table and any other related table?
		//userScoreboard.setTopicId(companyCandidates.get);
		userScoreboard.setUserSeq(candidateId);
		userScoreboard.setCompanyId(companyId);
		userScoreboard.setAssessmentId(assessmentId);
		userScoreboard.setTestDate(new Date());
		
		return userScoreboardRepository.save(userScoreboard);
	}
	
	@PostMapping("/candidates/login")
	public CompanyCandidates loginCandidates(@RequestBody ObjectNode objectNode) throws Exception{
		String email =  objectNode.get("email").asText();
		String accessCode =  objectNode.get("accessCode").asText();
		if(email == null || accessCode == null){
			throw new Exception("Email or Access code is null");
		}else{
			CompanyCandidates companyCandidates= companyCandidatesRepository.validateAccessCode(email, accessCode);
			if(companyCandidates !=  null){
				return companyCandidates;
			}
			throw new Exception("Email or Access code not match with existing record");
		}
	}
	
	@PostMapping("/candidates/getAssessment")
	public List<Assesment> getCandidatesAssessment(@RequestBody ObjectNode objectNode) throws ResourceNotFoundException{
		 List assessmnetList = assessmentRepository.getCandidatesAssessment(objectNode.get("email").asText());
		 System.out.println("CompanyController.getCandidatesAssessment()" + objectNode.get("email").asText());
		 if(assessmnetList == null || assessmnetList.size() == 0){
			throw new ResourceNotFoundException("Assessment not found ");
		 }else{
			 return assessmnetList;	 
		 }
	}
	@PostMapping("/candidates/getAssessmentBaseOnToken")
	public List<Assesment> getCandidatesAssessmentBaseOnToken(@RequestBody ObjectNode objectNode) throws ResourceNotFoundException{
		System.out.println("CompanyController + " +objectNode.get("accessCode").asText()); 
		List assessmnetList = assessmentRepository.getCandidatesAssessmentBaseOnToken(objectNode.get("accessCode").asText());
		if(assessmnetList == null || assessmnetList.size() == 0){
			throw new ResourceNotFoundException("Assessment not found ");
		}else{
			 return assessmnetList;	 
		}
	}
	
	@PostMapping("/signup")
	public Object signupCompany(@Valid @RequestBody Company company) throws Exception {
	  	ContactPerson contactPerson =new ContactPerson();
	  	contactPerson.setName(company.getYourName());
	  	contactPerson.setEmail(company.getCompanyEmail());
	  	contactPerson.setPhoneNo(company.getYourPhoneNo());
	  	contactPerson.setRoleId(company.getYourRoleId());
	  	company.getContactPersons().add(contactPerson);
	  	company.setPhoneNo(company.getYourPhoneNo());
	  	
	  	if(commonService.isListEmpty(userRepository.getByEmailId(company.getCompanyEmail()))){
	  		Message message =  new Message();
	  		message.setStatus("Error");
	  		message.setMessage("Duplicate Email id");
	  		return message;
	  	}
	  	
	  	ObjectMapper mapperObj = new ObjectMapper();
	  	try {
			company.setContactPerson(mapperObj.writeValueAsString(company.getContactPersons()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	  	Company company2 =  companyRepository.save(company);
	  	User user = new User();
	  	user.setEmail(company.getCompanyEmail());
	  	user.setFirstName(company.getCompanyName());
	  	user.setLastName(company.getCompanyName());
	  	user.setUserType(UserType.Admin);
	  	user.setUserName(company.getCompanyEmail());
	  	user.setPassword(CommonService.generatePassword(10));
	  	user.setCompanyId(company2);
	  	user = userRepository.save(user);
	  	if(user != null){
	  		try {
				commonService.sendEmail(user.getEmail(), "Access Your Compnay Login", user.getPassword());	
			}catch(MailAuthenticationException e){
				throw new Exception("Record save but email not send due to Username and password are invelid");
			}catch (Exception e) {
				throw e;
			}
	  	}
		return company2;
	}
}

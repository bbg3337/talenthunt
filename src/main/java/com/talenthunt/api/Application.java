package com.talenthunt.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talenthunt.api.bo.ContactPerson;
import com.talenthunt.api.enums.AssessmentMode;
import com.talenthunt.api.enums.DifficultyLevel;
import com.talenthunt.api.model.Assesment;
import com.talenthunt.api.model.Company;
import com.talenthunt.api.repository.AssessmentRepository;

/**
 * The type Application.
 *
 * @author Harmohan
 */
@SpringBootApplication
public class Application {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
	@Autowired
	static AssessmentRepository assessmentRepository;
	
  public static void main(String[] args) throws JsonProcessingException {
	  /*Assesment assesment =  new Assesment();
	  assesment.setName("Test");
	  assesment.setQuestionCount(10);
	  assesment.setAssessmentMode(AssessmentMode.ExamMode);
	  assesment.setDifficultyLevel(DifficultyLevel.Beginner);
	  assesment.setSubjects("sub");
	  assesment.setTags("tags");
	  assessmentRepository.save(assesment);*/
	  ObjectMapper mapperObj = new ObjectMapper();
	  	List<ContactPerson> contactPersons = new ArrayList<>();
	  	contactPersons.add(new ContactPerson());
	  	Company company = new Company();
	  	company.setContactPersons(contactPersons);
	  	
	  	System.out.println(mapperObj.writeValueAsString(company));
	  	
		SpringApplication.run(Application.class, args);
	}
}

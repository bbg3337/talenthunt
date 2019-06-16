package com.talenthunt.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.talenthunt.api.model.Assesment;
import com.talenthunt.api.model.AssesmentDetails;
import com.talenthunt.api.model.Questions;
import com.talenthunt.api.model.User;
import com.talenthunt.api.repository.AssessmentDetailsRepository;
import com.talenthunt.api.repository.AssessmentRepository;

import io.micrometer.core.ipc.http.HttpSender.Response;

/**
 * Controller for AssessMent
 * 
 * @author Harmohan
 *
 */

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AssesmentController 
{
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	@Autowired
	private AssessmentDetailsRepository assessmentDetailsRepository;
	
	
	
	/**
	 * Create Assessment 
	 * @param assessment
	 * @return
	 */
	@PostMapping("/assessment/create")
	  public Assesment createAssessment(@Valid @RequestBody Assesment assessment) {
	    List<AssesmentDetails> assesmentDetails = assessment.getAssesmentDetails();
	    assessment.setAssesmentDetails(null);
		Assesment asmnt = assessmentRepository.save(assessment);
	    for(AssesmentDetails assesmentDetails2 : assesmentDetails){
	    	assesmentDetails2.setAssessment(asmnt.getId());
	    	assessmentDetailsRepository.save(assesmentDetails2);
	    }
	    
		return asmnt; 
	    
	  }
	
	
	/**
	 * Get all assesment
	 * @return Assesment
	 */
	@GetMapping("/assessment")
	public List<Assesment> getAllAssessment() {
		return assessmentRepository.findAll();
	}
	
	/**
	 * Delete assesment
	 * @param assesmentId
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/assessment/{id}")
	public Map<String, Boolean> deleteAssessment(@PathVariable(value = "id") Long assesmentId) throws Exception {
		Assesment assessment = assessmentRepository.findById(assesmentId)
				.orElseThrow(() -> new ResourceNotFoundException(" assessment not found on :: " + assesmentId));

		assessmentRepository.delete(assessment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@GetMapping("/assessment/{id}")
	public ResponseEntity<Assesment> getAssesmentById(@PathVariable(value = "id") Long assesmentId) throws ResourceNotFoundException{
		Assesment assesment =
		        assessmentRepository
		            .findById(assesmentId)
		            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + assesmentId));
		
		return ResponseEntity.ok(assesment);
	}
	
	@PostMapping("/assessment/addQuestion/{id}")
	public List<AssesmentDetails> addQuestionInAssessment(@PathVariable(value = "id") Long assesmentId,@RequestBody ArrayList<Long> questionIds){
		List<AssesmentDetails> assesmentDetailsList = new ArrayList<>();
		System.out.println("AssesmentController.addQuestionInAssessment()" + questionIds);
		for(Long id : questionIds){
			AssesmentDetails assesmentDetails = new AssesmentDetails();
			assesmentDetails.setAssessment(assesmentId);
			assesmentDetails.setQuestions(new Questions(id));
			assesmentDetailsList.add(assessmentDetailsRepository.save(assesmentDetails));
			System.out.println("AssesmentController.addQuestionInAssessment()");
		}
		return assesmentDetailsList;
	}
}

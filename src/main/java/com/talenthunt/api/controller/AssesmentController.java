package com.talenthunt.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.talenthunt.api.model.User;
import com.talenthunt.api.repository.AssessmentRepository;

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
	AssessmentRepository assessmentRepository;
	
	
	
	/**
	 * Create Assessment 
	 * @param assessment
	 * @return
	 */
	@PostMapping("/assessment")
	  public Assesment createAssessment(@Valid @RequestBody Assesment assessment) {
	    return assessmentRepository.save(assessment);
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
		 Assesment assessment =
	    		assessmentRepository
	            .findById(assesmentId)
	            .orElseThrow(() -> new ResourceNotFoundException(" assessment not found on :: " + assesmentId));

	    assessmentRepository.delete(assessment);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	  }
}

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
import com.talenthunt.api.model.AssessmentPriceGrid;
import com.talenthunt.api.repository.AssessmentPriceGridRepository;

@RestController
@RequestMapping("/api/v1/assessmentPriceGrid")
@CrossOrigin
public class AssessmentPriceGridController {
	@Autowired
	private AssessmentPriceGridRepository assessmentPriceGridRepository;

	@PostMapping("/create")
	public AssessmentPriceGrid createAssessmentPriceGrid(@Valid @RequestBody AssessmentPriceGrid assessmentPriceGrid) {
		return assessmentPriceGridRepository.save(assessmentPriceGrid);
	}

	@GetMapping("/")
	public List<AssessmentPriceGrid> getAllAssessmentPriceGrid() {
		return assessmentPriceGridRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<AssessmentPriceGrid> getAssessmentPriceGridById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		AssessmentPriceGrid assessmentPriceGrid = assessmentPriceGridRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Assessment Price Grid not found on :: " + id));
		return ResponseEntity.ok().body(assessmentPriceGrid);
	}

	@PostMapping("/update")
	public AssessmentPriceGrid updateAssessmentPriceGrid(@Valid @RequestBody AssessmentPriceGrid assessmentPriceGrid) {
		AssessmentPriceGrid asmtPriceGrid = assessmentPriceGridRepository.getOne(assessmentPriceGrid.getId());
		asmtPriceGrid.setAssessmentId(assessmentPriceGrid.getAssessmentId());
		asmtPriceGrid.setAssessmentPrice(assessmentPriceGrid.getAssessmentPrice());
		asmtPriceGrid.setDiscountAmt(assessmentPriceGrid.getDiscountAmt());
		asmtPriceGrid.setDiscountCouponId(assessmentPriceGrid.getDiscountCouponId());
		asmtPriceGrid.setDiscountPct(assessmentPriceGrid.getDiscountPct());
		asmtPriceGrid.setFinalPrice(assessmentPriceGrid.getFinalPrice());
		asmtPriceGrid.setQuestionCount(assessmentPriceGrid.getQuestionCount());
		asmtPriceGrid.setValidFrom(assessmentPriceGrid.getValidFrom());
		asmtPriceGrid.setValidTo(assessmentPriceGrid.getValidTo());
		return assessmentPriceGridRepository.save(asmtPriceGrid);
	}
}

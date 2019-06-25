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
import com.talenthunt.api.model.DiscountGrid;
import com.talenthunt.api.repository.DiscountGridRepository;

@RestController
@RequestMapping("/api/v1/discountGrid")
@CrossOrigin
public class DiscountGridController {
	@Autowired
	private DiscountGridRepository discountGridRepository ;

	@PostMapping("/create")
	public DiscountGrid createDiscountGrid(@Valid @RequestBody DiscountGrid discountGrid) {
		return discountGridRepository.save(discountGrid);
	}

	@GetMapping("/")
	public List<DiscountGrid> getAllDiscountGrid() {
		return discountGridRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<DiscountGrid> getDiscountGridById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		DiscountGrid discountGrid = discountGridRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Discount Grid not found on :: " + id));
		return ResponseEntity.ok().body(discountGrid);
	}

	@PostMapping("/update")
	public DiscountGrid updateAssessmentPriceGrid(@Valid @RequestBody DiscountGrid discountGrid) {
		DiscountGrid disGrid = discountGridRepository.getOne(discountGrid.getDiscountId());
		disGrid.setDiscountActivateCount(discountGrid.getDiscountActivateCount());
		disGrid.setDiscountPct(discountGrid.getDiscountPct());
		disGrid.setSchemeName(discountGrid.getSchemeName());
		disGrid.setValidFrom(discountGrid.getValidFrom());
		disGrid.setValidTo(discountGrid.getValidTo());
		return discountGridRepository.save(disGrid);
	}
}

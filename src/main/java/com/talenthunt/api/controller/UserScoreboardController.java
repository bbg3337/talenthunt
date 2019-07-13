package com.talenthunt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Company;
import com.talenthunt.api.model.User;
import com.talenthunt.api.model.UserScoreboard;
import com.talenthunt.api.repository.UserScoreboardRepository;

@RestController
@RequestMapping("/api/v1/userScoreBoard")
@CrossOrigin
public class UserScoreboardController {

	@Autowired
	private UserScoreboardRepository userScoreboardRepository;
	
	@GetMapping("/getScore")
	public List<UserScoreboard> getCompanyList(@RequestParam("companyId")Long companyId){
		return userScoreboardRepository.getAllCandidatesAssessment(0l, companyId);
	}
	
}

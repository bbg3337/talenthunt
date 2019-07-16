package com.talenthunt.api.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talenthunt.api.bo.AssessmentQuestionAnswer;
import com.talenthunt.api.bo.QuestionText;
import com.talenthunt.api.bo.SubmitAssessment;
import com.talenthunt.api.enums.TestStatus;
import com.talenthunt.api.model.Assesment;
import com.talenthunt.api.model.AssesmentDetails;
import com.talenthunt.api.model.CompanyCandidates;
import com.talenthunt.api.model.Questions;
import com.talenthunt.api.model.UserScoreboard;
import com.talenthunt.api.repository.AssessmentRepository;
import com.talenthunt.api.repository.CompanyCandidatesRepository;
import com.talenthunt.api.repository.UserScoreboardRepository;

@Service
public class AssesmentService {

	@Autowired
	private AssessmentRepository assessmentRepository;
	@Autowired
	private CompanyCandidatesRepository companyCandidatesRepository;

	@Autowired
	private UserScoreboardRepository userScoreboardRepository;
	
	public UserScoreboard calculateAssessmentScore(@Valid SubmitAssessment submitAssessment) throws IOException {
		Assesment assesments = assessmentRepository.getAssesmentByQuestionOrder(submitAssessment.getAssessmentId());
		CompanyCandidates companyCandidates =companyCandidatesRepository.getOne(submitAssessment.getCandidateId()); 
		double totalScore =0;
		double grandTotal=0;
		int noQuestionsAtempted=0;
		Map<Long, Set<String>> queMap = new HashMap<>();
		for (AssessmentQuestionAnswer assessmentQuestionAnswer : submitAssessment.getAssessmnetQuestionAnswer()){
			queMap.put(assessmentQuestionAnswer.getQuestionId(), assessmentQuestionAnswer.getAnswerList());
		}
		for (AssesmentDetails assesmentDetail: assesments.getAssesmentDetails()) {
			Questions que = assesmentDetail.getQuestions();
			if(queMap.containsKey(que.getId())){
				++noQuestionsAtempted;
				ObjectMapper mapper = new ObjectMapper();
				try {
					QuestionText queText = mapper.readValue(que.getQuestionText(), QuestionText.class);
					grandTotal += que.getQuestionMarks();
					if(queText.getAns().equals(queMap.get(que.getId()))){
						totalScore+= que.getQuestionMarks();
					}else{
						totalScore-= que.getNegativeMarking();
					}
				} catch (IOException e) {
					e.printStackTrace();
					throw  new IOException("Cant not convert data into json");
				}

			}
		}
		try {
			UserScoreboard userScoreboard =  userScoreboardRepository.getOne(submitAssessment.getScoreboardId());
			userScoreboard.setStartAssessmentTime(submitAssessment.getStartDate());
			userScoreboard.setEndAssessmentTime(submitAssessment.getEndDate());
			userScoreboard.setTestStatus(TestStatus.Completed);
			userScoreboard.setNoQuestionsAtempted(noQuestionsAtempted);
			userScoreboard.setNoOfQuestions(assesments.getAssesmentDetails().size());
			userScoreboard.setScore(totalScore);
			return userScoreboard;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

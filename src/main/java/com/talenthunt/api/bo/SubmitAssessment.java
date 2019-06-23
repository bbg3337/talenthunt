package com.talenthunt.api.bo;

import java.util.List;

public class SubmitAssessment {

	private Long assessmentId;
	private Long candidateId ;

	private List<AssessmentQuestionAnswer> assessmnetQuestionAnswer;

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;	
	}

	public List<AssessmentQuestionAnswer> getAssessmnetQuestionAnswer() {
		return assessmnetQuestionAnswer;
	}

	public void setAssessmnetQuestionAnswer(List<AssessmentQuestionAnswer> assessmnetQuestionAnswer) {
		this.assessmnetQuestionAnswer = assessmnetQuestionAnswer;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
}
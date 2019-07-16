package com.talenthunt.api.bo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SubmitAssessment {

	private Long assessmentId;
	private Long candidateId ;
	private Long scoreboardId;
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	private Date startDate;
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	private Date endDate;
	
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

	public Long getScoreboardId() {
		return scoreboardId;
	}

	public void setScoreboardId(Long scoreboardId) {
		this.scoreboardId = scoreboardId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
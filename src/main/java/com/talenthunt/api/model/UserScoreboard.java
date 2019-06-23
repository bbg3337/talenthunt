package com.talenthunt.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talenthunt.api.enums.TestStatus;
import com.talenthunt.api.enums.TestType;

@Entity
@Table(name = "a2t_user_scoreboard")
@EntityListeners(AuditingEntityListener.class)
public class UserScoreboard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="scoreboard_id")
	private Long scoreboardId;
	
	@Column(name="user_seq")
	private Long userSeq;

	@Column(name="company_id")
	private Long companyId;
	
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	@Column(name="test_date")
	private Date testDate;

	@Column(name="no_of_questions")
	private Integer noOfQuestions;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "test_type", columnDefinition = "ENUM('Preparation', 'Mock_Test', 'Exam_Simulation')")
	private TestType testType;
	
	@Column(name="topic_id")
	private Integer topicId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "test_status", columnDefinition = "ENUM('Completed', 'InComplete')")
	private TestStatus testStatus;
	
	@Column(name="no_questions_atempted")
	private Integer noQuestionsAtempted;
	
	@Column(name="score")
	private Double score;

	public Long getScoreboardId() {
		return scoreboardId;
	}

	public void setScoreboardId(Long scoreboardId) {
		this.scoreboardId = scoreboardId;
	}

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public TestStatus getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(TestStatus testStatus) {
		this.testStatus = testStatus;
	}

	public Integer getNoQuestionsAtempted() {
		return noQuestionsAtempted;
	}

	public void setNoQuestionsAtempted(Integer noQuestionsAtempted) {
		this.noQuestionsAtempted = noQuestionsAtempted;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}

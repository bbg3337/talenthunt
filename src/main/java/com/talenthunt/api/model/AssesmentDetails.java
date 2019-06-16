package com.talenthunt.api.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Assesment model
 * @author Harmohan
 *
 */

@Entity
@Table(name = "a2t_assessment_details")
@EntityListeners(AuditingEntityListener.class)
public class AssesmentDetails 
{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",columnDefinition="int")
    private Long id;
	
	@Column(name="assessment_id",columnDefinition="int")
	private Long assessment;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "question_id", columnDefinition="int" )
	private Questions questions;
	
	@Transient
	ArrayList<Long> questionIds;

	public Long getAssessment() {
		return assessment;
	}

	public void setAssessment(Long assessment) {
		this.assessment = assessment;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<Long> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(ArrayList<Long> questionIds) {
		this.questionIds = questionIds;
	}
}

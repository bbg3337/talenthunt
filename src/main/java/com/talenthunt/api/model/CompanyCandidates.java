package com.talenthunt.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "a2t_company_candidates")
@EntityListeners(AuditingEntityListener.class)
public class CompanyCandidates {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="candidate_id")
	private Long id;
	
	@Column(name="marks_obtained_arith")
	private Integer marksObtainedArith;
	
	@Column(name="marks_obtaied_cogn")
	private Integer marksObtaiedCogn;
	
	@Column(name="marks_obtained_tech")
	private Integer marksObtainedTech;
	
	@Column(name="candidate_email_id")
	private String candidateEmailId;
	
	@Column(name="candidate_mobile")
	private String candidateMobile;
	
	@Column(name="candidate_name")
	private String candidateName;
	
	@JsonFormat(pattern="MM-dd-yyyy")
	@Column(name="assessment_sch_date")
	private Date assessmentSchDate;
	
	@JsonFormat(pattern="MM-dd-yyyy")
	@Column(name="assessment_valid_upto")
	private Date assessmentValidUpto;
	
	@Column(name="assessment_id")
	private Long assessmentId;
	
	@Column(name="company_id")
	private Long companyId;

	@Column(name="access_code")
	private String accessCode;
	
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	@Column(name="access_code_created_date")
	private Date accessCodeCreatedDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMarksObtainedArith() {
		return marksObtainedArith;
	}

	public void setMarksObtainedArith(Integer marksObtainedArith) {
		this.marksObtainedArith = marksObtainedArith;
	}

	public Integer getMarksObtaiedCogn() {
		return marksObtaiedCogn;
	}

	public void setMarksObtaiedCogn(Integer marksObtaiedCogn) {
		this.marksObtaiedCogn = marksObtaiedCogn;
	}

	public Integer getMarksObtainedTech() {
		return marksObtainedTech;
	}

	public void setMarksObtainedTech(Integer marksObtainedTech) {
		this.marksObtainedTech = marksObtainedTech;
	}

	public String getCandidateEmailId() {
		return candidateEmailId;
	}

	public void setCandidateEmailId(String candidateEmailId) {
		this.candidateEmailId = candidateEmailId;
	}

	public String getCandidateMobile() {
		return candidateMobile;
	}

	public void setCandidateMobile(String candidateMobile) {
		this.candidateMobile = candidateMobile;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public Date getAssessmentSchDate() {
		return assessmentSchDate;
	}

	public void setAssessmentSchDate(Date assessmentSchDate) {
		this.assessmentSchDate = assessmentSchDate;
	}

	public Date getAssessmentValidUpto() {
		return assessmentValidUpto;
	}

	public void setAssessmentValidUpto(Date assessmentValidUpto) {
		this.assessmentValidUpto = assessmentValidUpto;
	}

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public Date getAccessCodeCreatedDate() {
		return accessCodeCreatedDate;
	}

	public void setAccessCodeCreatedDate(Date accessCodeCreatedDate) {
		this.accessCodeCreatedDate = accessCodeCreatedDate;
	}
	
	
}

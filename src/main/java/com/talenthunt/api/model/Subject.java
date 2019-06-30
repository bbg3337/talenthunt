package com.talenthunt.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Subject Model
 * 
 * @author Harmohan
 *
 */

@Entity
@Table(name = "a2t_subjects")
@EntityListeners(AuditingEntityListener.class)
public class Subject {
	public Subject() {
	}
	public Subject(Long id) {
		this.subjectId = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subject_id", columnDefinition = "int")
	private Long subjectId;

	@Column(name = "subject_name", nullable = false)
	private String subjectName;

	@Column(name = "industry_id", columnDefinition = "int")
	private Long industryId;
	
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Long getIndustryId() {
		return industryId;
	}
	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

}

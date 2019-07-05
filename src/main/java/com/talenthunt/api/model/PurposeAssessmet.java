package com.talenthunt.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "a2t_purpose_assessmet")
@EntityListeners(AuditingEntityListener.class)
public class PurposeAssessmet {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column(name = "purpose_assessmet_text", nullable = false)
    private String purposeAssessmetText;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPurposeAssessmetText() {
		return purposeAssessmetText;
	}

	public void setPurposeAssessmetText(String purposeAssessmetText) {
		this.purposeAssessmetText = purposeAssessmetText;
	}
	
	
}

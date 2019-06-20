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
@Table(name = "a2t_industry_type")
@EntityListeners(AuditingEntityListener.class)
public class IndustryType {

	public IndustryType() {
	}

	public IndustryType(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "industry_type_id")
	private Long id;

	@Column(name = "industry_type", nullable = false)
	private String industryType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	
}

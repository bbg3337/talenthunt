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
@Table(name = "a2t_industries")
@EntityListeners(AuditingEntityListener.class)
public class Industries {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="industry_id" ,columnDefinition="int")
	private Long industryId;
	
	@Column(name="industry_name", length=400)
	private String industryName;
	
	@Column(name="industry_desc", length=400)
	private String industryDesc;

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustryDesc() {
		return industryDesc;
	}

	public void setIndustryDesc(String industryDesc) {
		this.industryDesc = industryDesc;
	}
}

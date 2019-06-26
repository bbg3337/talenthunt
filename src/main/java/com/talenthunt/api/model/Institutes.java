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
@Table(name = "a2t_institutes")
@EntityListeners(AuditingEntityListener.class)
public class Institutes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "institute_id")
	private Long instituteId;
	
	@Column(name="institute_name", length=255)
	private String instituteName;
	
	@Column(name="institute_web", length=255)
	private String instituteWeb;
	
	@Column(name="institute_desc", length=255)
	private String instituteDesc;
	
	@Column(name="institute_image", length=255)
	private String instituteImage;
	
	@Column(name="contact_pers_email", length=255)
	private String contactPersEmail;
	
	@Column(name="institute_email", length=255)
	private String instituteEmail;
	
	@Column(name="institute_logo_loc", length=255)
	private String instituteLogoLoc;

	@Column(name="institute_logo_name", length=255)
	private String instituteLogoName;

	@Column(name="institute_city", length=40)
	private String instituteCity;

	@Column(name="institute_state_cd", length=3)
	private String instituteStateCd;

	public Long getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(Long instituteId) {
		this.instituteId = instituteId;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getInstituteWeb() {
		return instituteWeb;
	}

	public void setInstituteWeb(String instituteWeb) {
		this.instituteWeb = instituteWeb;
	}

	public String getInstituteDesc() {
		return instituteDesc;
	}

	public void setInstituteDesc(String instituteDesc) {
		this.instituteDesc = instituteDesc;
	}

	public String getInstituteImage() {
		return instituteImage;
	}

	public void setInstituteImage(String instituteImage) {
		this.instituteImage = instituteImage;
	}

	public String getContactPersEmail() {
		return contactPersEmail;
	}

	public void setContactPersEmail(String contactPersEmail) {
		this.contactPersEmail = contactPersEmail;
	}

	public String getInstituteEmail() {
		return instituteEmail;
	}

	public void setInstituteEmail(String instituteEmail) {
		this.instituteEmail = instituteEmail;
	}

	public String getInstituteLogoLoc() {
		return instituteLogoLoc;
	}

	public void setInstituteLogoLoc(String instituteLogoLoc) {
		this.instituteLogoLoc = instituteLogoLoc;
	}

	public String getInstituteLogoName() {
		return instituteLogoName;
	}

	public void setInstituteLogoName(String instituteLogoName) {
		this.instituteLogoName = instituteLogoName;
	}

	public String getInstituteCity() {
		return instituteCity;
	}

	public void setInstituteCity(String instituteCity) {
		this.instituteCity = instituteCity;
	}

	public String getInstituteStateCd() {
		return instituteStateCd;
	}

	public void setInstituteStateCd(String instituteStateCd) {
		this.instituteStateCd = instituteStateCd;
	}
	
}

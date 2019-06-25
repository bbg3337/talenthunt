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
@Table(name = "a2t_discount_grid")
@EntityListeners(AuditingEntityListener.class)
public class DiscountGrid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_id", columnDefinition = "int")
	private Long discountId;
	
	@Column(name="scheme_name")
	private String schemeName;
	
	@JsonFormat(pattern="MM-dd-yyyy")
	@Column(name="valid_from")
	private Date validFrom;

	@JsonFormat(pattern="MM-dd-yyyy")
	@Column(name="valid_to")
	private Date validTo;
	
	@Column(name="discount_pct")
	private Double discountPct;
	
	@Column(name="discount_activate_count")
	private Integer discountActivateCount;

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Double getDiscountPct() {
		return discountPct;
	}

	public void setDiscountPct(Double discountPct) {
		this.discountPct = discountPct;
	}

	public Integer getDiscountActivateCount() {
		return discountActivateCount;
	}

	public void setDiscountActivateCount(Integer discountActivateCount) {
		this.discountActivateCount = discountActivateCount;
	}

}

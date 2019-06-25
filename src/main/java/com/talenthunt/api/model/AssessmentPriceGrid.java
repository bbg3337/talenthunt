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
@Table(name = "a2t_asmnt_price_grid")
@EntityListeners(AuditingEntityListener.class)
public class AssessmentPriceGrid {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private Long id;
	
	@Column(name="assessment_id",columnDefinition="int")
    private Long assessmentId;
	
	@Column(name = "no_of_questions", nullable = false)
    private int questionCount;
	
	@JsonFormat(pattern="MM-dd-yyyy")
	@Column(name="valid_from")
	private Date validFrom;

	@JsonFormat(pattern="MM-dd-yyyy")
	@Column(name="valid_to")
	private Date validTo;
	
	@Column(name="assessment_price")
	private Double assessmentPrice;
	
	@Column(name="discount_coupon_id")
	private Integer discountCouponId;
	
	@Column(name="discount_pct")
	private Double discountPct;
	
	@Column(name="discount_amt")
	private Double discountAmt;
	
	@Column(name="final_price")
	private Double finalPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
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

	public Double getAssessmentPrice() {
		return assessmentPrice;
	}

	public void setAssessmentPrice(Double assessmentPrice) {
		this.assessmentPrice = assessmentPrice;
	}

	public Integer getDiscountCouponId() {
		return discountCouponId;
	}

	public void setDiscountCouponId(Integer discountCouponId) {
		this.discountCouponId = discountCouponId;
	}

	public Double getDiscountPct() {
		return discountPct;
	}

	public void setDiscountPct(Double discountPct) {
		this.discountPct = discountPct;
	}

	public Double getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(Double discountAmt) {
		this.discountAmt = discountAmt;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
}

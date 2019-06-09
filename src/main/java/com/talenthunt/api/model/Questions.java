package com.talenthunt.api.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.talenthunt.api.enums.DifficultyLevel;

@Entity
@Table(name = "a2t_question_bank")
@EntityListeners(AuditingEntityListener.class)
public class Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="question_id",columnDefinition="int")
	private Long id;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "topic_id",columnDefinition="int")
	private Topic topic = new Topic();
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id",columnDefinition="int")
	private Subject subject = new Subject();
	
	@Column(name = "question",columnDefinition="TEXT")
	private String questionText;

	@Enumerated(EnumType.STRING)
	@Column(name="difficulty_level", columnDefinition="ENUM('Beginner','Intermediate','Expert')" )
	private DifficultyLevel difficultyLevel;
	
	@Column(name="tags",columnDefinition="TEXT")
	private String tags;

	@Column(name="explanation",columnDefinition="TEXT")
	private String explanation;
	
	@Column(name = "marks",columnDefinition="int")
	private Double questionMarks;

	@Column(name = "negative_marking",columnDefinition="int")
	private Double negativeMarking;

	@Column(name="reference_text",columnDefinition="TEXT")
	private String referenceText;
	
	@Column(name = "reference_image",columnDefinition="TEXT")
	private String referenceImage;
	
	@Column(name="question_type_cd",columnDefinition="Char")
	private String questionTypeCD;
	
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", nullable = false)
    private Date createdOn;

    @Column(name = "created_by", nullable = false,columnDefinition="int")
    @CreatedBy
    private Long createdBy;

    /*@Column(name = "language_id")
	private long languageId;

	@Column(name = "complexity_id")
	private long complexityId;

	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category = new Category();


	@Column(name = "question_type_id")
	private long questionTypeId;

	@Column(name = "question_hint_text")
	private String questionHintText;

	@Column(name = "is_active")
	private Boolean isActive;*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Double getQuestionMarks() {
		return questionMarks;
	}

	public void setQuestionMarks(Double questionMarks) {
		this.questionMarks = questionMarks;
	}

	public Double getNegativeMarking() {
		return negativeMarking;
	}

	public void setNegativeMarking(Double negativeMarking) {
		this.negativeMarking = negativeMarking;
	}

	public String getReferenceText() {
		return referenceText;
	}

	public void setReferenceText(String referenceText) {
		this.referenceText = referenceText;
	}

	public String getReferenceImage() {
		return referenceImage;
	}

	public void setReferenceImage(String referenceImage) {
		this.referenceImage = referenceImage;
	}

	public String getQuestionTypeCD() {
		return questionTypeCD;
	}

	public void setQuestionTypeCD(String questionTypeCD) {
		this.questionTypeCD = questionTypeCD;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
}

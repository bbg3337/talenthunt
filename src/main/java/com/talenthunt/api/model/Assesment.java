package com.talenthunt.api.model;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.talenthunt.api.enums.AssessmentMode;
import com.talenthunt.api.enums.DifficultyLevel;

/**
 * Assesment model
 * @author Harmohan
 *
 */

@Entity
@Table(name = "a2t_assessments")
@EntityListeners(AuditingEntityListener.class)
public class Assesment 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="assessment_id",columnDefinition="int")
    private Long id;
	
	@Column(name = "assessment_name", nullable = false)
    private String name;
	
/*	@Column(name = "description", nullable = false)
    private String description;*/
	
/*	@Column(name = "mark", nullable = false)
    private int mark;*/
	
	@Column(name = "no_of_questions", nullable = false)
    private int questionCount;
	
	@Column(name="tags",columnDefinition="TEXT")
	private String tags;
	
	@Column(name="subjects", columnDefinition="TEXT")
	private String subjects;
	
	@Enumerated(EnumType.STRING)
	@Column(name="difficulty_level", columnDefinition="ENUM('Beginner','Intermediate','Expert')" )
	private DifficultyLevel difficultyLevel;
	

	@Enumerated(EnumType.STRING)
	@Column(name="assessment_mode" , columnDefinition="ENUM('Regular_Mode', 'Exam_Mode')")
	private AssessmentMode assessmentMode;
	
	/*@Column(name = "duration", nullable = false)
    private int duration;
	
	@Column(name = "edit_flag", nullable = false)
    private int flagEdit;
	
	@Column(name = "back_flag", nullable = false)
    private int flagBack;*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "assessment")
	private List<AssesmentDetails> assesmentDetails;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public AssessmentMode getAssessmentMode() {
		return assessmentMode;
	}

	public void setAssessmentMode(AssessmentMode assessmentMode) {
		this.assessmentMode = assessmentMode;
	}

	public List<AssesmentDetails> getAssesmentDetails() {
		return assesmentDetails;
	}

	public void setAssesmentDetails(List<AssesmentDetails> assesmentDetails) {
		this.assesmentDetails = assesmentDetails;
	}
}

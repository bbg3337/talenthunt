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
 * Assesment model
 * @author Harmohan
 *
 */

@Entity
@Table(name = "assesment")
@EntityListeners(AuditingEntityListener.class)
public class Assesment 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "name", nullable = false)
    private String name;
	
	@Column(name = "description", nullable = false)
    private String description;
	
	@Column(name = "mark", nullable = false)
    private int mark;
	
	@Column(name = "question_count", nullable = false)
    private int questionCount;
	
	@Column(name = "duration", nullable = false)
    private int duration;
	
	@Column(name = "edit_flag", nullable = false)
    private int flagEdit;
	
	@Column(name = "back_flag", nullable = false)
    private int flagBack;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getFlagEdit() {
		return flagEdit;
	}

	public void setFlagEdit(int flagEdit) {
		this.flagEdit = flagEdit;
	}

	public int getFlagBack() {
		return flagBack;
	}

	public void setFlagBack(int flagBack) {
		this.flagBack = flagBack;
	}
	
	

}

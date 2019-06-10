package com.talenthunt.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Subject Topic Model class
 * @author Harmohan
 *
 */


@Entity
@Table(name = "a2t_topics")
@EntityListeners(AuditingEntityListener.class)
public class Topic
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="topic_id", columnDefinition="int")
    private Long id;

    @Column(name = "topic_name", nullable = false)
    private String name;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}

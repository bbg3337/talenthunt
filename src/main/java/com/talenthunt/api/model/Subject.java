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
 * Subject Model
 * @author Harmohan
 *
 */

@Entity
@Table(name = "a2t_subjects")
@EntityListeners(AuditingEntityListener.class)
public class Subject 
{
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="subject_id", columnDefinition="int")
	    private long subjectId;

		@Column(name = "subject_name", nullable = false)
	    private String subjectName;

		public long getSubjectId() {
			return subjectId;
		}

		public void setSubjectId(long subjectId) {
			this.subjectId = subjectId;
		}

		public String getSubjectName() {
			return subjectName;
		}

		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}
	    
}

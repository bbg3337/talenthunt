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
	    private long id;

	    @Column(name = "name", nullable = false)
	    private String name;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "category_id")
	    private Category category;

	    @Column(name = "licence_type", nullable = false)
	    private String licenceType;
	    
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

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public String getLicenceType() {
			return licenceType;
		}

		public void setLicenceType(String licenceType) {
			this.licenceType = licenceType;
		}

}

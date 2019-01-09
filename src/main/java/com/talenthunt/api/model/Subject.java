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
@Table(name = "subjects")
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

}

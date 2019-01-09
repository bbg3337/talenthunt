package com.talenthunt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Topic;

/**
 * The interface topic repository.
 *
 * @author Harmohan
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> 
{

	/**
	 * Addition method for getting topic with subject id
	 * @param subjectId
	 * @return
	 */
	@Query("SELECT t.* FROM Topic t WHERE t.subject.id = :subjectId")
	List<Topic> findBySubjectId(@Param("subjectId") Long subjectId);
	
}

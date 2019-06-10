package com.talenthunt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Questions;

/**
 * The interface User repository.
 *Question repository
 *
 * @author Harmohan
 */
@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long>
{
	@Query(value = "SELECT * FROM question Q WHERE Q.category_id = :categoryid AND "
			+ "Q.subject_id= :subjectid AND +Q.topic_id= :topicid AND language_id = :languageId", nativeQuery = true)
	List<Questions> getByQuestionType(@Param("categoryid")Long categoryid,@Param("subjectid") Long subjectid,@Param("topicid") Long topicid,@Param("languageId") Long languageId);
	
}

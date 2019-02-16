package com.talenthunt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Question;

/**
 * The interface User repository.
 *Question repository
 *
 * @author Harmohan
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>
{

	/**
	 * Get Question for assesment with subject, topic, category with limit of question
	 * @param categoryid
	 * @param subjectid
	 * @param topicid
	 * @param questioncount
	 * @return List<Question>
	 */
	@Query(value = "SELECT * FROM questions Q WHERE Q.category_id = :categoryid AND "
			+ "Q.subject_id= :subjectid AND +Q.topic_id= :topicid LIMIT :questioncount", nativeQuery = true)
	List<Question> getAssesment(@Param("categoryid")Long categoryid,@Param("subjectid") Long subjectid,@Param("topicid") Long topicid,@Param("questioncount") Long questioncount);
	
}

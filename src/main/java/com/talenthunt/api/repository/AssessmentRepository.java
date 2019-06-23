package com.talenthunt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Assesment;

/**
 * The interface User repository.
 *
 * @author Harmohan
 */
@Repository
public interface AssessmentRepository extends JpaRepository<Assesment, Long> {
	public static final String candidateAssement="SELECT * FROM a2t_assessments ass "
			+ "WHERE ass.assessment_id in "
			+ "(select candidate.assessment_id from a2t_company_candidates candidate "
			+ "where candidate.candidate_email_id =:emailId)";
	public static final String assessmentByQuestionOrder = "SELECT * FROM talent_hunt.a2t_assessments ast "
			+ "Join talent_hunt.a2t_assessment_details astdtl on astdtl.assessment_id =ast.assessment_id "
			+ "join talent_hunt.a2t_question_bank que on que.question_id =  astdtl.question_id "
			+ "where ast.assessment_id = :id"
			+ " order by que.question_id ";
	
	@Query(value = candidateAssement, nativeQuery = true)
	public List<Assesment> getCandidatesAssessment(@Param("emailId")String emailId);
	
	@Query(value = assessmentByQuestionOrder, nativeQuery = true)
	public Assesment getAssesmentByQuestionOrder(@Param("id")Long id);

}

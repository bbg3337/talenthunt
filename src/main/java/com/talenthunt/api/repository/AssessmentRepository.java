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
	@Query(value = candidateAssement, nativeQuery = true)
	public List<Assesment> getCandidatesAssessment(@Param("emailId")String emailId);

}

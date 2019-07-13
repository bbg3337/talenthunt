package com.talenthunt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.talenthunt.api.model.UserScoreboard;

public interface UserScoreboardRepository  extends JpaRepository<UserScoreboard, Long> {

	public static final String userScoreBoardBaseOnCandidateIdAndAssignmentId=
			"select * from a2t_user_scoreboard where (0 = :userSeq or user_seq = :userSeq) and company_id= :companyId ";
	
	@Query(value = userScoreBoardBaseOnCandidateIdAndAssignmentId, nativeQuery = true)
	public UserScoreboard getCandidatesAssessment(@Param("userSeq")Long userSeq,@Param("companyId")Long companyId);
	
	@Query(value = userScoreBoardBaseOnCandidateIdAndAssignmentId, nativeQuery = true)
	public List<UserScoreboard> getAllCandidatesAssessment(@Param("userSeq")Long userSeq,@Param("companyId")Long companyId);
}

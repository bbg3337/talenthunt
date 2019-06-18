package com.talenthunt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.CompanyCandidates;

@Repository
public interface CompanyCandidatesRepository extends JpaRepository<CompanyCandidates, Long> {

	@Query(value = "SELECT * FROM a2t_company_candidates WHERE company_id = :companyId", nativeQuery = true)
	public List<CompanyCandidates> getCandidateByCompanyId(@Param("companyId")Long companyId);

	@Query(value = "SELECT * FROM a2t_company_candidates WHERE candidate_email_id = :candidate_email_id and access_code = :accessCode", nativeQuery = true)
	public CompanyCandidates validateAccessCode(@Param("candidate_email_id")String candidateEmailId,@Param("accessCode") String accessCode);

	
}

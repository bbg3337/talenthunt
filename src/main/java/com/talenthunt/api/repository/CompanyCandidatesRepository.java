package com.talenthunt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Company;
import com.talenthunt.api.model.CompanyCandidates;

@Repository
public interface CompanyCandidatesRepository extends JpaRepository<CompanyCandidates, Long> {

	@Query(value = "SELECT * FROM a2t_company_candidates WHERE company_id = :companyId", nativeQuery = true)
	List<CompanyCandidates> getCandidateByCompanyId(@Param("companyId")Long companyId);

}

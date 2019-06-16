package com.talenthunt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Questions;
import com.talenthunt.api.model.User;

/**
 * The interface User repository.
 *
 * @author Harmohan
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT * FROM a2t_users user WHERE user.email_address = :emailId", nativeQuery = true)
	List<User> getByEmailId(@Param("emailId")String categoryid);

	@Query(value = "SELECT * FROM a2t_users user WHERE user.company_id= :companyId", nativeQuery = true)
	List<User> getByCompanyId(@Param("companyId")Long companyId);
	
	
}

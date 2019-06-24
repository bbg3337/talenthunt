package com.talenthunt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talenthunt.api.model.Industries;

@Repository
public interface IndustriesRepository extends JpaRepository<Industries, Long>{

}

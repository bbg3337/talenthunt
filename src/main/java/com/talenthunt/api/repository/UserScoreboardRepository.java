package com.talenthunt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talenthunt.api.model.UserScoreboard;

public interface UserScoreboardRepository  extends JpaRepository<UserScoreboard, Long> {

}

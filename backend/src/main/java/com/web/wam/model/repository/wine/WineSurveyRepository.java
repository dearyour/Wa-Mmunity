package com.web.wam.model.repository.wine;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.wam.model.entity.WineSurvey;

public interface WineSurveyRepository extends JpaRepository<WineSurvey, Integer> {
	Optional<WineSurvey> findByMemberId(int memberId);
}

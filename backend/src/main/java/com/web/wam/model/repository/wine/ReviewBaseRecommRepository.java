package com.web.wam.model.repository.wine;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.web.wam.model.entity.ReviewBaseRecomm;

public interface ReviewBaseRecommRepository extends JpaRepository<ReviewBaseRecomm, Integer> {

	@Transactional
	@Modifying
	@Query(value = "truncate review_base_recomm", nativeQuery = true)
	void truncate();
}
package com.web.wam.model.repository.wine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.wam.model.entity.WineReview;

public interface WineReviewRepository extends JpaRepository<WineReview, Integer> {
	List<WineReview> findByMemberId(int memberId);

	List<WineReview> findByWineId(int wineId);
}

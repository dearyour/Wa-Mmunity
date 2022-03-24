package com.web.wam.model.repository.wine;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.wam.model.entity.WineReview;

public interface WineReviewRepository extends JpaRepository<WineReview, Integer> {

}

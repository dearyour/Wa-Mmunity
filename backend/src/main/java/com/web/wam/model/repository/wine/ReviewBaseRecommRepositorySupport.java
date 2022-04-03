package com.web.wam.model.repository.wine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.QReviewBaseRecomm;
import com.web.wam.model.entity.ReviewBaseRecomm;

@Repository
public class ReviewBaseRecommRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QReviewBaseRecomm qReviewBaseRecomm = QReviewBaseRecomm.reviewBaseRecomm;

	public List<ReviewBaseRecomm> findByMemberId(int memberId) {
		List<ReviewBaseRecomm> list = jpaQueryFactory.select(qReviewBaseRecomm).from(qReviewBaseRecomm)
				.where(qReviewBaseRecomm.memberId.eq(memberId)).orderBy(qReviewBaseRecomm.expRating.desc()).fetch();
		return list;
	}

}

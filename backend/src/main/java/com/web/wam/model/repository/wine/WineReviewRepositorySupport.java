package com.web.wam.model.repository.wine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.QWineReview;

@Repository
public class WineReviewRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QWineReview qWineReview = QWineReview.wineReview;

	public double sumRatingByMemberId(int memberId) {
		jpaQueryFactory.select(qWineReview).from(qWineReview).where(qWineReview.memberId.eq(memberId));
		return 0;
	}
}

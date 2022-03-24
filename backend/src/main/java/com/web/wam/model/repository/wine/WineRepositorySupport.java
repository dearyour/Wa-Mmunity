package com.web.wam.model.repository.wine;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.QWine;
import com.web.wam.model.entity.Wine;

@Repository
public class WineRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QWine qWine = QWine.wine;

	public List<Wine> sortByRatingAvg() {
		List<Wine> wines = jpaQueryFactory.select(qWine).from(qWine).orderBy(qWine.ratingAvg.desc()).fetch();
		return wines;
	}

	public List<Wine> sortByLowPrice() {
		List<Wine> wines = jpaQueryFactory.select(qWine).from(qWine).orderBy(qWine.price.asc()).fetch();
		return wines;
	}

	public List<Wine> sortByHighPrice() {
		List<Wine> wines = jpaQueryFactory.select(qWine).from(qWine).orderBy(qWine.price.desc()).fetch();
		return wines;
	}

	public List<Wine> sortByRatingNum() {
		List<Wine> wines = jpaQueryFactory.select(qWine).from(qWine).orderBy(qWine.ratingNum.desc()).fetch();
		return wines;
	}

	public List<Wine> searchWineByKeyword(String keyword) {
		String key = "%" + keyword + "%";
		List<Wine> wines = jpaQueryFactory.select(qWine).from(qWine).where(qWine.name.like(key)).fetch();
		return wines;
	}

	@Transactional
	public void setRatingAvg(int wineId, double rating) {
		double curRatingAvg = jpaQueryFactory.select(qWine.ratingAvg).from(qWine).where(qWine.wineId.eq(wineId))
				.fetchOne();
		int curRatingNum = jpaQueryFactory.select(qWine.ratingNum).from(qWine).where(qWine.wineId.eq(wineId))
				.fetchOne();
		double newRatingAvg = (curRatingAvg * curRatingNum + rating) / (curRatingNum + 1);
		jpaQueryFactory.update(qWine).where(qWine.wineId.eq(wineId)).set(qWine.ratingAvg, newRatingAvg).execute();
	}

	@Transactional
	public void updateRatingCnt(int wineId, int cnt) {
		int curRatingNum = jpaQueryFactory.select(qWine.ratingNum).from(qWine).where(qWine.wineId.eq(wineId))
				.fetchOne();
		jpaQueryFactory.update(qWine).where(qWine.wineId.eq(wineId)).set(qWine.ratingNum, curRatingNum + cnt).execute();
	}

}

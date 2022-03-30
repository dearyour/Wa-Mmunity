package com.web.wam.model.repository.wine;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.dto.wine.WineFilterRequest;
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

	public List<Wine> findByFilter(WineFilterRequest filter) {
		BooleanBuilder styleBuilder = new BooleanBuilder();
		BooleanBuilder coutryBuilder = new BooleanBuilder();
		BooleanBuilder regionBuilder = new BooleanBuilder();

		List<String> wineStyle = filter.getWineStyle();
		List<String> countries = filter.getCountry();
		List<String> regions = filter.getRegion();

		int minPrice = filter.getMinPrice();
		int maxPrice = filter.getMaxPrice();
		int minRate = filter.getMinRate();
		int maxRate = filter.getMaxRate();

		if (!wineStyle.isEmpty()) {
			for (String style : wineStyle) {
				styleBuilder.or(qWine.cat1.like("%" + style + "%"));
			}
		}

		if (!countries.isEmpty()) {
			for (String country : countries) {
				coutryBuilder.or(qWine.country.like("%" + country + "%"));
			}
		}

		if (!regions.isEmpty()) {

			for (String region : regions) {
				regionBuilder.or(qWine.region1.like("%" + region + "%"));
				regionBuilder.or(qWine.region2.like("%" + region + "%"));
				regionBuilder.or(qWine.region3.like("%" + region + "%"));
			}
		}

		List<Wine> wines = jpaQueryFactory.select(qWine).from(qWine).where(qWine.price.between(minPrice, maxPrice))
				.where(qWine.ratingAvg.between(minRate, maxRate)).where(styleBuilder).where(coutryBuilder)
				.where(regionBuilder).fetch();

		return wines;
	}

}

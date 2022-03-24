package com.web.wam.model.repository.wine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.QWineWishlist;

@Repository
public class WineWishlistRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QWineWishlist wineWishlist = QWineWishlist.wineWishlist;

	public List<Integer> searchWishlistByMemberId(int memberId) {
		List<Integer> wishlist = jpaQueryFactory.select(wineWishlist.wineId).from(wineWishlist)
				.where(wineWishlist.memberId.eq(memberId)).fetch();
		return wishlist;
	}

}

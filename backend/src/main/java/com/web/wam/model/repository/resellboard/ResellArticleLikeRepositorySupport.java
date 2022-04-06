package com.web.wam.model.repository.resellboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.dto.resellboard.ResellBoardLikePostRequest;
import com.web.wam.model.entity.resellboard.QResellArticleLike;

@Repository
public class ResellArticleLikeRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	QResellArticleLike qResellArticleLike = QResellArticleLike.resellArticleLike;

	public long countByArticleId(Integer articleId) {
		long likeCnt = jpaQueryFactory.select(qResellArticleLike).from(qResellArticleLike)
				.where(qResellArticleLike.articleId.eq(articleId)).fetchCount();
		return likeCnt;
	}

	@Transactional
	public void cancelLike(ResellBoardLikePostRequest likeCancelInfo) {
		jpaQueryFactory.delete(qResellArticleLike).where(qResellArticleLike.articleId.eq(likeCancelInfo.getArticleId()))
				.where(qResellArticleLike.memberId.eq(likeCancelInfo.getMemberId())).execute();
	}

	public List<Integer> getLikeArticleByMemberId(int memberId) {
		List<Integer> articles = jpaQueryFactory.select(qResellArticleLike.articleId).from(qResellArticleLike)
				.where(qResellArticleLike.memberId.eq(memberId)).fetch();
		return articles;
	}
}

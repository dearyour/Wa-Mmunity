package com.web.wam.model.repository.freeboard;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.freeboard.FreeArticleComment;
import com.web.wam.model.entity.freeboard.QFreeArticleComment;

@Repository
public class FreeArticleCommentRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QFreeArticleComment qFreeArticleComment = QFreeArticleComment.freeArticleComment;
	
	public List<FreeArticleComment> findByArticleId(int articleId) {
		List<FreeArticleComment> comments = jpaQueryFactory.select(qFreeArticleComment).from(qFreeArticleComment).where(qFreeArticleComment.articleId.eq(articleId)).fetch();
		return comments;
	}

	public List<FreeArticleComment> findByMemberId(int memberId) {
		List<FreeArticleComment> comments = jpaQueryFactory.select(qFreeArticleComment).from(qFreeArticleComment).where(qFreeArticleComment.memberId.eq(memberId)).fetch();
		return comments;
	}

}


package com.web.wam.model.repository.freeboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.freeboard.FreeArticleComment;

@Repository
public class FreeArticleCommentRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
//	QFreeArticleComment qFreeArticleComment = QFreeArticleComment.freeArticleComment;
	
	public List<FreeArticleComment> findByArticleId(int articleId) {
		
		return null;
	}
}

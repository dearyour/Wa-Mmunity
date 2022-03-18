package com.web.wam.model.repository.freeboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.freeboard.FreeArticleComment;
import com.web.wam.model.entity.freeboard.FreeBoard;
import com.web.wam.model.entity.freeboard.QFreeArticleComment;
import com.web.wam.model.entity.freeboard.QFreeBoard;

@Repository
public class FreeBoardRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QFreeBoard qFreeBoard = QFreeBoard.freeBoard;
	
	public List<FreeBoard> findByMemberId(int memberId) {
		List<FreeBoard> articles = jpaQueryFactory.select(qFreeBoard).from(qFreeBoard).where(qFreeBoard.memberId.eq(memberId)).fetch();
		return articles;
	}

}


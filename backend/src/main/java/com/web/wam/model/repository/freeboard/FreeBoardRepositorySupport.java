package com.web.wam.model.repository.freeboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Autowired
	FreeArticleLikeRepositorySupport freeArticleLikeRepositorySupport;
	
	public Map<FreeBoard, Long> findByMemberId(int memberId) {
		Map<FreeBoard, Long> articleList = new HashMap<FreeBoard, Long>();
		
		List<FreeBoard> articles = jpaQueryFactory.select(qFreeBoard).from(qFreeBoard).where(qFreeBoard.memberId.eq(memberId)).fetch();
		for(FreeBoard article :articles) {
			long likeCnt = freeArticleLikeRepositorySupport.countByArticleId(article.getArticleId());
			articleList.put(article,likeCnt);
		}
		return articleList;
	}

	public Map<FreeBoard, Long> getArticleByKeyword(String keyword) {
		String key = "%"+keyword+"%";
		Map<FreeBoard, Long> articleList = new HashMap<FreeBoard, Long>();
		
		List<FreeBoard> articles = jpaQueryFactory.select(qFreeBoard).from(qFreeBoard).where(qFreeBoard.title.like(key)).fetch();
		for(FreeBoard article :articles) {
			long likeCnt = freeArticleLikeRepositorySupport.countByArticleId(article.getArticleId());
			articleList.put(article,likeCnt);
		}
		return articleList;
	}

}


package com.web.wam.model.repository.freeboard;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.dto.freeboard.FreeBoardResponse;
import com.web.wam.model.entity.freeboard.FreeBoard;
import com.web.wam.model.entity.freeboard.QFreeBoard;

@Repository
public class FreeBoardRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QFreeBoard qFreeBoard = QFreeBoard.freeBoard;
	
	@Autowired
	FreeArticleLikeRepositorySupport freeArticleLikeRepositorySupport;
	
	public List<FreeBoardResponse> findByMemberId(int memberId) {
		List<FreeBoardResponse> articleList = new LinkedList<FreeBoardResponse>();
		List<FreeBoard> articles = jpaQueryFactory.select(qFreeBoard).from(qFreeBoard).where(qFreeBoard.memberId.eq(memberId)).fetch();
		for(FreeBoard article :articles) {
			FreeBoardResponse freeBoardResponse = new FreeBoardResponse();
			setFreeBoardResponse(article, freeBoardResponse);	
			articleList.add(freeBoardResponse);
		}
		return articleList;
	}
	
	private void setFreeBoardResponse(FreeBoard article, FreeBoardResponse freeBoardResponse) {
		freeBoardResponse.setArticleId(article.getArticleId());
		freeBoardResponse.setMemberId(article.getMemberId());
		freeBoardResponse.setTitle(article.getTitle());
		freeBoardResponse.setContent(article.getContent());
		freeBoardResponse.setPhoto(article.getPhoto());
		freeBoardResponse.setTag(article.getTag());
		freeBoardResponse.setRegtime(article.getRegtime());
		freeBoardResponse.setLikeCnt(freeArticleLikeRepositorySupport.countByArticleId(article.getArticleId()));
	}


	public List<FreeBoardResponse> getArticleByKeyword(String keyword) {
		String key = "%"+keyword+"%";
		List<FreeBoardResponse> articleList = new LinkedList<FreeBoardResponse>();
		
		List<FreeBoard> articles = jpaQueryFactory.select(qFreeBoard).from(qFreeBoard).where(qFreeBoard.title.like(key)).fetch();
		for(FreeBoard article :articles) {
			FreeBoardResponse freeBoardResponse = new FreeBoardResponse();
			setFreeBoardResponse(article, freeBoardResponse);	
			articleList.add(freeBoardResponse);
		}
		return articleList;
	}

}


package com.web.wam.model.repository.resellboard;

import java.util.LinkedList;
import java.util.List;

import com.web.wam.model.entity.QMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.dto.resellboard.ResellBoardResponse;
import com.web.wam.model.entity.resellboard.QResellBoard;
import com.web.wam.model.entity.resellboard.ResellBoard;

@Repository
public class ResellBoardRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QResellBoard qResellBoard = QResellBoard.resellBoard;
	QMember qMember = QMember.member;

	@Autowired
	ResellArticleLikeRepositorySupport resellArticleLikeRepositorySupport;

	public List<ResellBoardResponse> findByMemberId(int memberId) {
		List<ResellBoardResponse> articleList = new LinkedList<ResellBoardResponse>();
		List<ResellBoard> articles = jpaQueryFactory.select(qResellBoard).from(qResellBoard)
				.where(qResellBoard.memberId.eq(memberId)).fetch();
		for (ResellBoard article : articles) {
			ResellBoardResponse resellBoardResponse = new ResellBoardResponse();
			setResellBoardResponse(article, resellBoardResponse);
			articleList.add(resellBoardResponse);
		}
		return articleList;
	}

	private void setResellBoardResponse(ResellBoard article, ResellBoardResponse resellBoardResponse) {
		resellBoardResponse.setId(article.getId());
		resellBoardResponse.setMemberId(article.getMemberId());
		resellBoardResponse.setTitle(article.getTitle());
		resellBoardResponse.setContent(article.getContent());
		resellBoardResponse.setPhoto(article.getPhoto());
		resellBoardResponse.setTag(article.getTag());
		resellBoardResponse.setRegtime(article.getRegtime());
		resellBoardResponse.setPrice(article.getPrice());
		resellBoardResponse.setLikeCnt(resellArticleLikeRepositorySupport.countByArticleId(article.getId()));
		resellBoardResponse.setMemberName(findMemberNameById(article.getMemberId()));
	}

	public List<ResellBoardResponse> getArticleByKeyword(String keyword) {
		String key = "%" + keyword + "%";
		List<ResellBoardResponse> articleList = new LinkedList<ResellBoardResponse>();

		List<ResellBoard> articles = jpaQueryFactory.select(qResellBoard).from(qResellBoard)
				.where(qResellBoard.title.like(key)).fetch();
		for (ResellBoard article : articles) {
			ResellBoardResponse resellBoardResponse = new ResellBoardResponse();
			setResellBoardResponse(article, resellBoardResponse);
			articleList.add(resellBoardResponse);
		}
		return articleList;
	}

	public String findMemberNameById(Integer memberId) {

		String name = jpaQueryFactory.select(qMember.nickname).from(qMember)
				.where(qMember.id.eq(memberId)).fetchOne();
		return name;
	}
}

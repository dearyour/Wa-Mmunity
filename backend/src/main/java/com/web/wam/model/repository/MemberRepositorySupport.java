package com.web.wam.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.QMember;

@Repository
public class MemberRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QMember qMember = QMember.member;

	public String getNickNameByMemberId(int memberId) {
		String nickname = jpaQueryFactory.select(qMember.nickname).from(qMember).where(qMember.id.eq(memberId))
				.fetchFirst();
		return nickname;
	}

}

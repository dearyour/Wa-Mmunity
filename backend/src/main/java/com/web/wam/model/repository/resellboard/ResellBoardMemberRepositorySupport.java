package com.web.wam.model.repository.resellboard;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.QMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResellBoardMemberRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QMember qMemberBoard = QMember.member;

    public String findMemberNameById(Integer memberId) {

        String name = jpaQueryFactory.select(qMemberBoard.nickname).from(qMemberBoard)
                .where(qMemberBoard.id.eq(memberId)).fetchOne();
        return name;
    }

}

package com.web.wam.model.repository.resellboard;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.entity.Member;
import com.web.wam.model.entity.QMember;
import com.web.wam.model.entity.resellboard.QResellArticleComment;
import com.web.wam.model.entity.resellboard.ResellArticleComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResellArticleCommentRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QResellArticleComment qResellArticleComment = QResellArticleComment.resellArticleComment;
    QMember qMember = QMember.member;

    public List<ResellArticleComment> findByArticleId(int articleId) {
        List<ResellArticleComment> comments = jpaQueryFactory.select(qResellArticleComment).from(qResellArticleComment).where(qResellArticleComment.articleId.eq(articleId)).fetch();
        return comments;
    }

    public List<ResellArticleComment> findByMemberId(int memberId) {
        List<ResellArticleComment> comments = jpaQueryFactory.select(qResellArticleComment).from(qResellArticleComment).where(qResellArticleComment.memberId.eq(memberId)).fetch();
        return comments;
    }

    public String findMemberNicknameByMemberId(Integer memberId) {
        return jpaQueryFactory.select(qMember.nickname).from(qMember).where(qMember.id.eq(memberId)).fetchOne();
    }
}

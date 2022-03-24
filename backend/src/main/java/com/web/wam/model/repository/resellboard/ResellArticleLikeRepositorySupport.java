package com.web.wam.model.repository.resellboard;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.wam.model.dto.resellboard.ResellArticleLikePostRequest;
import com.web.wam.model.entity.resellboard.QResellArticleLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ResellArticleLikeRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QResellArticleLike qResellArticleLike = QResellArticleLike.resellArticleLike;

    public long countByArticleId(Integer articleId) {
        long likeCnt = jpaQueryFactory.select(qResellArticleLike)
                .from(qResellArticleLike)
                .where(qResellArticleLike.articleId.eq(articleId))
                .fetchCount();
        return likeCnt;
    }

    @Transactional
    public void cancelLike(ResellArticleLikePostRequest likeCancelInfo) {
        jpaQueryFactory.delete(qResellArticleLike)
                .where(qResellArticleLike.articleId.eq(likeCancelInfo.getArticleId()))
                .where(qResellArticleLike.memberId.eq(likeCancelInfo.getMemberId()))
                .execute();
    }
}

package com.web.wam.model.entity.resellboard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResellArticleComment is a Querydsl query type for ResellArticleComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResellArticleComment extends EntityPathBase<ResellArticleComment> {

    private static final long serialVersionUID = -392721361L;

    public static final QResellArticleComment resellArticleComment = new QResellArticleComment("resellArticleComment");

    public final NumberPath<Integer> articleId = createNumber("articleId", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> regtime = createDateTime("regtime", java.time.LocalDateTime.class);

    public QResellArticleComment(String variable) {
        super(ResellArticleComment.class, forVariable(variable));
    }

    public QResellArticleComment(Path<? extends ResellArticleComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResellArticleComment(PathMetadata metadata) {
        super(ResellArticleComment.class, metadata);
    }

}


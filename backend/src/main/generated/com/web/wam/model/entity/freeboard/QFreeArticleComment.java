package com.web.wam.model.entity.freeboard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFreeArticleComment is a Querydsl query type for FreeArticleComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeArticleComment extends EntityPathBase<FreeArticleComment> {

    private static final long serialVersionUID = -943014659L;

    public static final QFreeArticleComment freeArticleComment = new QFreeArticleComment("freeArticleComment");

    public final NumberPath<Integer> articleId = createNumber("articleId", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> regtime = createDateTime("regtime", java.time.LocalDateTime.class);

    public QFreeArticleComment(String variable) {
        super(FreeArticleComment.class, forVariable(variable));
    }

    public QFreeArticleComment(Path<? extends FreeArticleComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeArticleComment(PathMetadata metadata) {
        super(FreeArticleComment.class, metadata);
    }

}


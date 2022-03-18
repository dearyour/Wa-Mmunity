package com.web.wam.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = 616160844L;

    public static final QNotice notice = new QNotice("notice");

    public final NumberPath<Integer> articleId = createNumber("articleId", Integer.class);

    public final NumberPath<Integer> boardId = createNumber("boardId", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public QNotice(String variable) {
        super(Notice.class, forVariable(variable));
    }

    public QNotice(Path<? extends Notice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotice(PathMetadata metadata) {
        super(Notice.class, metadata);
    }

}


package com.web.wam.model.entity.freeboard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFreeArticleLike is a Querydsl query type for FreeArticleLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeArticleLike extends EntityPathBase<FreeArticleLike> {

    private static final long serialVersionUID = -1498704455L;

    public static final QFreeArticleLike freeArticleLike = new QFreeArticleLike("freeArticleLike");

    public final NumberPath<Integer> articleId = createNumber("articleId", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public QFreeArticleLike(String variable) {
        super(FreeArticleLike.class, forVariable(variable));
    }

    public QFreeArticleLike(Path<? extends FreeArticleLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeArticleLike(PathMetadata metadata) {
        super(FreeArticleLike.class, metadata);
    }

}


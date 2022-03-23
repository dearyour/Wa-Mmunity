package com.web.wam.model.entity.resellboard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResellArticleLike is a Querydsl query type for ResellArticleLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResellArticleLike extends EntityPathBase<ResellArticleLike> {

    private static final long serialVersionUID = -2033556537L;

    public static final QResellArticleLike resellArticleLike = new QResellArticleLike("resellArticleLike");

    public final NumberPath<Integer> articleId = createNumber("articleId", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public QResellArticleLike(String variable) {
        super(ResellArticleLike.class, forVariable(variable));
    }

    public QResellArticleLike(Path<? extends ResellArticleLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResellArticleLike(PathMetadata metadata) {
        super(ResellArticleLike.class, metadata);
    }

}


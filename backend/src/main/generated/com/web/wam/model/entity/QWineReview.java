package com.web.wam.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWineReview is a Querydsl query type for WineReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWineReview extends EntityPathBase<WineReview> {

    private static final long serialVersionUID = 437851701L;

    public static final QWineReview wineReview = new QWineReview("wineReview");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public final StringPath rating = createString("rating");

    public final DateTimePath<java.time.LocalDateTime> regtime = createDateTime("regtime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> wineId = createNumber("wineId", Integer.class);

    public QWineReview(String variable) {
        super(WineReview.class, forVariable(variable));
    }

    public QWineReview(Path<? extends WineReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWineReview(PathMetadata metadata) {
        super(WineReview.class, metadata);
    }

}


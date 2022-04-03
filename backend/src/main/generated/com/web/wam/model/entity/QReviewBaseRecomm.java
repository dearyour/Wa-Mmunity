package com.web.wam.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewBaseRecomm is a Querydsl query type for ReviewBaseRecomm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewBaseRecomm extends EntityPathBase<ReviewBaseRecomm> {

    private static final long serialVersionUID = 1791969756L;

    public static final QReviewBaseRecomm reviewBaseRecomm = new QReviewBaseRecomm("reviewBaseRecomm");

    public final NumberPath<Double> expRating = createNumber("expRating", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public final NumberPath<Integer> wineId = createNumber("wineId", Integer.class);

    public QReviewBaseRecomm(String variable) {
        super(ReviewBaseRecomm.class, forVariable(variable));
    }

    public QReviewBaseRecomm(Path<? extends ReviewBaseRecomm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewBaseRecomm(PathMetadata metadata) {
        super(ReviewBaseRecomm.class, metadata);
    }

}


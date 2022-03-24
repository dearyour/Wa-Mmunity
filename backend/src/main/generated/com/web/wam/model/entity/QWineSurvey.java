package com.web.wam.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWineSurvey is a Querydsl query type for WineSurvey
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWineSurvey extends EntityPathBase<WineSurvey> {

    private static final long serialVersionUID = 481150519L;

    public static final QWineSurvey wineSurvey = new QWineSurvey("wineSurvey");

    public final NumberPath<Integer> acidicPreference = createNumber("acidicPreference", Integer.class);

    public final NumberPath<Integer> amountOfAlcohol = createNumber("amountOfAlcohol", Integer.class);

    public final NumberPath<Integer> boldPreference = createNumber("boldPreference", Integer.class);

    public final NumberPath<Integer> food1 = createNumber("food1", Integer.class);

    public final NumberPath<Integer> food2 = createNumber("food2", Integer.class);

    public final NumberPath<Integer> food3 = createNumber("food3", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> maxPrice = createNumber("maxPrice", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public final NumberPath<Integer> minPrice = createNumber("minPrice", Integer.class);

    public final NumberPath<Integer> smellTaste1 = createNumber("smellTaste1", Integer.class);

    public final NumberPath<Integer> smellTaste2 = createNumber("smellTaste2", Integer.class);

    public final NumberPath<Integer> smellTaste3 = createNumber("smellTaste3", Integer.class);

    public final NumberPath<Integer> sweetPreference = createNumber("sweetPreference", Integer.class);

    public final NumberPath<Integer> tannicPreference = createNumber("tannicPreference", Integer.class);

    public QWineSurvey(String variable) {
        super(WineSurvey.class, forVariable(variable));
    }

    public QWineSurvey(Path<? extends WineSurvey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWineSurvey(PathMetadata metadata) {
        super(WineSurvey.class, metadata);
    }

}


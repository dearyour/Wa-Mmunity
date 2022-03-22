package com.web.wam.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWine is a Querydsl query type for Wine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWine extends EntityPathBase<Wine> {

    private static final long serialVersionUID = -1107475331L;

    public static final QWine wine = new QWine("wine");

    public final NumberPath<Double> acidic = createNumber("acidic", Double.class);

    public final NumberPath<Double> alcoholContent = createNumber("alcoholContent", Double.class);

    public final StringPath allergens = createString("allergens");

    public final NumberPath<Double> bold = createNumber("bold", Double.class);

    public final StringPath cat1 = createString("cat1");

    public final StringPath cat2 = createString("cat2");

    public final StringPath country = createString("country");

    public final StringPath foodParings = createString("foodParings");

    public final StringPath grapes = createString("grapes");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Double> ratingAvg = createNumber("ratingAvg", Double.class);

    public final NumberPath<Integer> ratingNum = createNumber("ratingNum", Integer.class);

    public final StringPath regions = createString("regions");

    public final StringPath style = createString("style");

    public final NumberPath<Double> sweet = createNumber("sweet", Double.class);

    public final NumberPath<Double> tannic = createNumber("tannic", Double.class);

    public final StringPath taste = createString("taste");

    public final NumberPath<Integer> wineId = createNumber("wineId", Integer.class);

    public final StringPath winery = createString("winery");

    public QWine(String variable) {
        super(Wine.class, forVariable(variable));
    }

    public QWine(Path<? extends Wine> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWine(PathMetadata metadata) {
        super(Wine.class, metadata);
    }

}


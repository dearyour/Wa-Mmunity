package com.web.wam.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWineWishlist is a Querydsl query type for WineWishlist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWineWishlist extends EntityPathBase<WineWishlist> {

    private static final long serialVersionUID = -838758046L;

    public static final QWineWishlist wineWishlist = new QWineWishlist("wineWishlist");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public final NumberPath<Integer> wineId = createNumber("wineId", Integer.class);

    public QWineWishlist(String variable) {
        super(WineWishlist.class, forVariable(variable));
    }

    public QWineWishlist(Path<? extends WineWishlist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWineWishlist(PathMetadata metadata) {
        super(WineWishlist.class, metadata);
    }

}


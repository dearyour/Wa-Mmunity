package com.web.wam.model.entity.resellboard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResellBoard is a Querydsl query type for ResellBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResellBoard extends EntityPathBase<ResellBoard> {

    private static final long serialVersionUID = 1388374144L;

    public static final QResellBoard resellBoard = new QResellBoard("resellBoard");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public final StringPath photo = createString("photo");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> regtime = createDateTime("regtime", java.time.LocalDateTime.class);

    public final StringPath tag = createString("tag");

    public final StringPath title = createString("title");

    public QResellBoard(String variable) {
        super(ResellBoard.class, forVariable(variable));
    }

    public QResellBoard(Path<? extends ResellBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResellBoard(PathMetadata metadata) {
        super(ResellBoard.class, metadata);
    }

}


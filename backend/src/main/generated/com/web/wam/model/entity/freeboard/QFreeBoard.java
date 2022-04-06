package com.web.wam.model.entity.freeboard;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.processing.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

/**
 * QFreeBoard is a Querydsl query type for FreeBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoard extends EntityPathBase<FreeBoard> {

	private static final long serialVersionUID = 2015557618L;

	public static final QFreeBoard freeBoard = new QFreeBoard("freeBoard");

	public final NumberPath<Integer> articleId = createNumber("articleId", Integer.class);

	public final StringPath content = createString("content");

	public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

	public final StringPath photo = createString("photo");

	public final DateTimePath<java.time.LocalDateTime> regtime = createDateTime("regtime",
			java.time.LocalDateTime.class);

	public final StringPath tag = createString("tag");

	public final StringPath title = createString("title");

	public QFreeBoard(String variable) {
		super(FreeBoard.class, forVariable(variable));
	}

	public QFreeBoard(Path<? extends FreeBoard> path) {
		super(path.getType(), path.getMetadata());
	}

	public QFreeBoard(PathMetadata metadata) {
		super(FreeBoard.class, metadata);
	}

}

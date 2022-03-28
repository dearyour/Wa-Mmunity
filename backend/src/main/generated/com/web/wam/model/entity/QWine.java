package com.web.wam.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.processing.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

/**
 * QWine is a Querydsl query type for Wine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWine extends EntityPathBase<Wine> {

	private static final long serialVersionUID = -1107475331L;

	public static final QWine wine = new QWine("wine");

	public final NumberPath<Double> acidic = createNumber("acidic", Double.class);

	public final NumberPath<Double> ageing = createNumber("ageing", Double.class);

	public final NumberPath<Double> alcoholContent = createNumber("alcoholContent", Double.class);

	public final StringPath allergen1 = createString("allergen1");

	public final StringPath allergen2 = createString("allergen2");

	public final StringPath allergen3 = createString("allergen3");

	public final NumberPath<Double> beef = createNumber("beef", Double.class);

	public final NumberPath<Double> blackFruit = createNumber("blackFruit", Double.class);

	public final NumberPath<Double> bold = createNumber("bold", Double.class);

	public final StringPath cat1 = createString("cat1");

	public final StringPath cat2 = createString("cat2");

	public final NumberPath<Double> citrus = createNumber("citrus", Double.class);

	public final StringPath country = createString("country");

	public final NumberPath<Double> curedMeat = createNumber("curedMeat", Double.class);

	public final NumberPath<Double> driedFruit = createNumber("driedFruit", Double.class);

	public final NumberPath<Double> earthy = createNumber("earthy", Double.class);

	public final NumberPath<Double> floral = createNumber("floral", Double.class);

	public final NumberPath<Double> game = createNumber("game", Double.class);

	public final StringPath grape1 = createString("grape1");

	public final StringPath grape2 = createString("grape2");

	public final StringPath grape3 = createString("grape3");

	public final StringPath img = createString("img");

	public final NumberPath<Double> lamb = createNumber("lamb", Double.class);

	public final NumberPath<Double> matureAndHardCheese = createNumber("matureAndHardCheese", Double.class);

	public final StringPath name = createString("name");

	public final NumberPath<Double> oaky = createNumber("oaky", Double.class);

	public final NumberPath<Double> pasta = createNumber("pasta", Double.class);

	public final NumberPath<Double> pork = createNumber("pork", Double.class);

	public final NumberPath<Double> poultry = createNumber("poultry", Double.class);

	public final NumberPath<Integer> price = createNumber("price", Integer.class);

	public final NumberPath<Double> ratingAvg = createNumber("ratingAvg", Double.class);

	public final NumberPath<Integer> ratingNum = createNumber("ratingNum", Integer.class);

	public final NumberPath<Double> redFruit = createNumber("redFruit", Double.class);

	public final StringPath region1 = createString("region1");

	public final StringPath region2 = createString("region2");

	public final StringPath region3 = createString("region3");

	public final NumberPath<Double> spices = createNumber("spices", Double.class);

	public final NumberPath<Double> sweet = createNumber("sweet", Double.class);

	public final NumberPath<Double> tannic = createNumber("tannic", Double.class);

	public final NumberPath<Double> treeFruit = createNumber("treeFruit", Double.class);

	public final NumberPath<Double> tropical = createNumber("tropical", Double.class);

	public final NumberPath<Double> veal = createNumber("veal", Double.class);

	public final NumberPath<Double> vegetal = createNumber("vegetal", Double.class);

	public final NumberPath<Integer> wineId = createNumber("wineId", Integer.class);

	public final StringPath winery = createString("winery");

	public final StringPath wineStyle = createString("wineStyle");

	public final NumberPath<Double> yeasty = createNumber("yeasty", Double.class);

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

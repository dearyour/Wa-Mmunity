package com.web.wam.model.dto.wine;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("WineResponse")
public class WineResponse implements Serializable {

	@ApiModelProperty(name = "Wine wineId", example = "123")
	private int wineId;

	@ApiModelProperty(name = "Wine name", example = "Essencia 2000")
	private String name;

	@ApiModelProperty(name = "Wine price", example = "940515")
	private Integer price;

	@ApiModelProperty(name = "Wine winery", example = "Royal Tokaji")
	private String winery;

	@ApiModelProperty(name = "Wine grapes", example = "Yellow Muscat,Hárslevelű,Furmint")
	private String grapes;

	@ApiModelProperty(name = "Wine country", example = "Hungary")
	private String country;

	@ApiModelProperty(name = "Wine regions", example = "Tokaj")
	private String regions;

	@ApiModelProperty(name = "Wine allergens", example = "sulfites")
	private String allergens;

	@ApiModelProperty(name = "Wine cat1", example = "Dessert wine")
	private String cat1;

	@ApiModelProperty(name = "Wine cat2", example = "Blend")
	private String cat2;

	@ApiModelProperty(name = "Wine ratingAvg", example = "4.8")
	private Double ratingAvg;

	@ApiModelProperty(name = "Wine ratingNum", example = "39")
	private Integer ratingNum;

	@ApiModelProperty(name = "Wine taste", example = "earthy,oaky,citrus")
	private String taste;

	@ApiModelProperty(name = "Wine acidic", example = "76")
	private Double acidic;

	@ApiModelProperty(name = "Wine alcoholContent", example = "14")
	private Double alcoholContent;

	@ApiModelProperty(name = "Wine bold", example = "84")
	private Double bold;

	@ApiModelProperty(name = "Wine tannic", example = "123")
	private Double tannic;

	@ApiModelProperty(name = "Wine sweet", example = "85")
	private Double sweet;

	@ApiModelProperty(name = "Wine style", example = "Bordeaux Sauternes")
	private String style;

	@ApiModelProperty(name = "Wine foodParings", example = "Beef,Lamb")
	private String foodParings;
}
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

	@ApiModelProperty(name = "Wine name", example = "123")
	private String name;

	@ApiModelProperty(name = "Wine img", example = "123")
	private String img;

	@ApiModelProperty(name = "Wine winery", example = "123")
	private String winery;

	@ApiModelProperty(name = "Wine wineStyle", example = "123")
	private String wineStyle;

	@ApiModelProperty(name = "Wine country", example = "123")
	private String country;

	@ApiModelProperty(name = "Wine region1", example = "123")
	private String region1;

	@ApiModelProperty(name = "Wine region2", example = "123")
	private String region2;

	@ApiModelProperty(name = "Wine region3", example = "123")
	private String region3;

	@ApiModelProperty(name = "Wine grape1", example = "123")
	private String grape1;

	@ApiModelProperty(name = "Wine grape2", example = "123")
	private String grape2;

	@ApiModelProperty(name = "Wine grape3", example = "123")
	private String grape3;

	@ApiModelProperty(name = "Wine cat1", example = "123")
	private String cat1;

	@ApiModelProperty(name = "Wine cat2", example = "123")
	private String cat2;

	@ApiModelProperty(name = "Wine price", example = "123")
	private int price;

	@ApiModelProperty(name = "Wine alcoholContent", example = "123")
	private Double alcoholContent;

	@ApiModelProperty(name = "Wine allergen1", example = "123")
	private String allergen1;

	@ApiModelProperty(name = "Wine allergen2", example = "123")
	private String allergen2;

	@ApiModelProperty(name = "Wine allergen3", example = "123")
	private String allergen3;

	@ApiModelProperty(name = "Wine ratingAvg", example = "123")
	private Double ratingAvg;

	@ApiModelProperty(name = "Wine ratingNum", example = "123")
	private Integer ratingNum;

	@ApiModelProperty(name = "Wine oaky", example = "123")
	private Double oaky;

	@ApiModelProperty(name = "Wine earthy", example = "123")
	private Double earthy;

	@ApiModelProperty(name = "Wine blackFruit", example = "123")
	private Double blackFruit;

	@ApiModelProperty(name = "Wine redFruit", example = "123")
	private Double redFruit;

	@ApiModelProperty(name = "Wine spices", example = "123")
	private Double spices;

	@ApiModelProperty(name = "Wine floral", example = "123")
	private Double floral;

	@ApiModelProperty(name = "Wine driedFruit", example = "123")
	private Double driedFruit;

	@ApiModelProperty(name = "Wine ageing", example = "123")
	private Double ageing;

	@ApiModelProperty(name = "Wine yeasty", example = "123")
	private Double yeasty;

	@ApiModelProperty(name = "Wine vegetal", example = "123")
	private Double vegetal;

	@ApiModelProperty(name = "Wine citrus", example = "123")
	private Double citrus;

	@ApiModelProperty(name = "Wine treeFruit", example = "123")
	private Double treeFruit;

	@ApiModelProperty(name = "Wine bold", example = "123")
	private Double bold;

	@ApiModelProperty(name = "Wine tannic", example = "123")
	private Double tannic;

	@ApiModelProperty(name = "Wine sweet", example = "123")
	private Double sweet;

	@ApiModelProperty(name = "Wine acidic", example = "123")
	private Double acidic;

	@ApiModelProperty(name = "Wine beef", example = "123")
	private Double beef;

	@ApiModelProperty(name = "Wine lamb", example = "123")
	private Double lamb;

	@ApiModelProperty(name = "Wine game", example = "123")
	private Double game;

	@ApiModelProperty(name = "Wine poultry", example = "123")
	private Double poultry;

	@ApiModelProperty(name = "Wine tropical", example = "123")
	private Double tropical;

	@ApiModelProperty(name = "Wine pasta", example = "123")
	private Double pasta;

	@ApiModelProperty(name = "Wine veal", example = "123")
	private Double veal;

	@ApiModelProperty(name = "Wine curedMeat", example = "123")
	private Double curedMeat;

	@ApiModelProperty(name = "Wine matureAndHardCheese", example = "123")
	private Double matureAndHardCheese;

	@ApiModelProperty(name = "Wine pork", example = "123")
	private Double pork;
}
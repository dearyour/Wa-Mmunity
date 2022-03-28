package com.web.wam.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wine", schema = "wamunity", catalog = "")
public class Wine {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "wine_id")
	private int wineId;
	@Basic
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "img")
	private String img;
	@Basic
	@Column(name = "winery")
	private String winery;
	@Basic
	@Column(name = "wine_style")
	private String wineStyle;
	@Basic
	@Column(name = "country")
	private String country;
	@Basic
	@Column(name = "region1")
	private String region1;
	@Basic
	@Column(name = "region2")
	private String region2;
	@Basic
	@Column(name = "region3")
	private String region3;
	@Basic
	@Column(name = "grape1")
	private String grape1;
	@Basic
	@Column(name = "grape2")
	private String grape2;
	@Basic
	@Column(name = "grape3")
	private String grape3;
	@Basic
	@Column(name = "cat1")
	private String cat1;
	@Basic
	@Column(name = "cat2")
	private String cat2;
	@Basic
	@Column(name = "price")
	private int price;
	@Basic
	@Column(name = "alcohol_content")
	private Double alcoholContent;
	@Basic
	@Column(name = "allergen1")
	private String allergen1;
	@Basic
	@Column(name = "allergen2")
	private String allergen2;
	@Basic
	@Column(name = "allergen3")
	private String allergen3;
	@Basic
	@Column(name = "rating_avg")
	private Double ratingAvg;
	@Basic
	@Column(name = "rating_num")
	private Integer ratingNum;
	@Basic
	@Column(name = "oaky")
	private Double oaky;
	@Basic
	@Column(name = "earthy")
	private Double earthy;
	@Basic
	@Column(name = "black_fruit")
	private Double blackFruit;
	@Basic
	@Column(name = "red_fruit")
	private Double redFruit;
	@Basic
	@Column(name = "spices")
	private Double spices;
	@Basic
	@Column(name = "floral")
	private Double floral;
	@Basic
	@Column(name = "dried_fruit")
	private Double driedFruit;
	@Basic
	@Column(name = "ageing")
	private Double ageing;
	@Basic
	@Column(name = "yeasty")
	private Double yeasty;
	@Basic
	@Column(name = "vegetal")
	private Double vegetal;
	@Basic
	@Column(name = "citrus")
	private Double citrus;
	@Basic
	@Column(name = "tree_fruit")
	private Double treeFruit;
	@Basic
	@Column(name = "bold")
	private Double bold;
	@Basic
	@Column(name = "tannic")
	private Double tannic;
	@Basic
	@Column(name = "sweet")
	private Double sweet;
	@Basic
	@Column(name = "acidic")
	private Double acidic;
	@Basic
	@Column(name = "beef")
	private Double beef;
	@Basic
	@Column(name = "lamb")
	private Double lamb;
	@Basic
	@Column(name = "game")
	private Double game;
	@Basic
	@Column(name = "poultry")
	private Double poultry;
	@Basic
	@Column(name = "tropical")
	private Double tropical;
	@Basic
	@Column(name = "pasta")
	private Double pasta;
	@Basic
	@Column(name = "veal")
	private Double veal;
	@Basic
	@Column(name = "cured_meat")
	private Double curedMeat;
	@Basic
	@Column(name = "mature_and_hard_cheese")
	private Double matureAndHardCheese;
	@Basic
	@Column(name = "pork")
	private Double pork;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acidic == null) ? 0 : acidic.hashCode());
		result = prime * result + ((ageing == null) ? 0 : ageing.hashCode());
		result = prime * result + ((alcoholContent == null) ? 0 : alcoholContent.hashCode());
		result = prime * result + ((allergen1 == null) ? 0 : allergen1.hashCode());
		result = prime * result + ((allergen2 == null) ? 0 : allergen2.hashCode());
		result = prime * result + ((allergen3 == null) ? 0 : allergen3.hashCode());
		result = prime * result + ((beef == null) ? 0 : beef.hashCode());
		result = prime * result + ((blackFruit == null) ? 0 : blackFruit.hashCode());
		result = prime * result + ((bold == null) ? 0 : bold.hashCode());
		result = prime * result + ((cat1 == null) ? 0 : cat1.hashCode());
		result = prime * result + ((cat2 == null) ? 0 : cat2.hashCode());
		result = prime * result + ((citrus == null) ? 0 : citrus.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((curedMeat == null) ? 0 : curedMeat.hashCode());
		result = prime * result + ((driedFruit == null) ? 0 : driedFruit.hashCode());
		result = prime * result + ((earthy == null) ? 0 : earthy.hashCode());
		result = prime * result + ((floral == null) ? 0 : floral.hashCode());
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((grape1 == null) ? 0 : grape1.hashCode());
		result = prime * result + ((grape2 == null) ? 0 : grape2.hashCode());
		result = prime * result + ((grape3 == null) ? 0 : grape3.hashCode());
		result = prime * result + ((img == null) ? 0 : img.hashCode());
		result = prime * result + ((lamb == null) ? 0 : lamb.hashCode());
		result = prime * result + ((matureAndHardCheese == null) ? 0 : matureAndHardCheese.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((oaky == null) ? 0 : oaky.hashCode());
		result = prime * result + ((pasta == null) ? 0 : pasta.hashCode());
		result = prime * result + ((pork == null) ? 0 : pork.hashCode());
		result = prime * result + ((poultry == null) ? 0 : poultry.hashCode());
		result = prime * result + price;
		result = prime * result + ((ratingAvg == null) ? 0 : ratingAvg.hashCode());
		result = prime * result + ((ratingNum == null) ? 0 : ratingNum.hashCode());
		result = prime * result + ((redFruit == null) ? 0 : redFruit.hashCode());
		result = prime * result + ((region1 == null) ? 0 : region1.hashCode());
		result = prime * result + ((region2 == null) ? 0 : region2.hashCode());
		result = prime * result + ((region3 == null) ? 0 : region3.hashCode());
		result = prime * result + ((spices == null) ? 0 : spices.hashCode());
		result = prime * result + ((sweet == null) ? 0 : sweet.hashCode());
		result = prime * result + ((tannic == null) ? 0 : tannic.hashCode());
		result = prime * result + ((treeFruit == null) ? 0 : treeFruit.hashCode());
		result = prime * result + ((tropical == null) ? 0 : tropical.hashCode());
		result = prime * result + ((veal == null) ? 0 : veal.hashCode());
		result = prime * result + ((vegetal == null) ? 0 : vegetal.hashCode());
		result = prime * result + ((wineStyle == null) ? 0 : wineStyle.hashCode());
		result = prime * result + wineId;
		result = prime * result + ((winery == null) ? 0 : winery.hashCode());
		result = prime * result + ((yeasty == null) ? 0 : yeasty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wine other = (Wine) obj;
		if (acidic == null) {
			if (other.acidic != null)
				return false;
		} else if (!acidic.equals(other.acidic))
			return false;
		if (ageing == null) {
			if (other.ageing != null)
				return false;
		} else if (!ageing.equals(other.ageing))
			return false;
		if (alcoholContent == null) {
			if (other.alcoholContent != null)
				return false;
		} else if (!alcoholContent.equals(other.alcoholContent))
			return false;
		if (allergen1 == null) {
			if (other.allergen1 != null)
				return false;
		} else if (!allergen1.equals(other.allergen1))
			return false;
		if (allergen2 == null) {
			if (other.allergen2 != null)
				return false;
		} else if (!allergen2.equals(other.allergen2))
			return false;
		if (allergen3 == null) {
			if (other.allergen3 != null)
				return false;
		} else if (!allergen3.equals(other.allergen3))
			return false;
		if (beef == null) {
			if (other.beef != null)
				return false;
		} else if (!beef.equals(other.beef))
			return false;
		if (blackFruit == null) {
			if (other.blackFruit != null)
				return false;
		} else if (!blackFruit.equals(other.blackFruit))
			return false;
		if (bold == null) {
			if (other.bold != null)
				return false;
		} else if (!bold.equals(other.bold))
			return false;
		if (cat1 == null) {
			if (other.cat1 != null)
				return false;
		} else if (!cat1.equals(other.cat1))
			return false;
		if (cat2 == null) {
			if (other.cat2 != null)
				return false;
		} else if (!cat2.equals(other.cat2))
			return false;
		if (citrus == null) {
			if (other.citrus != null)
				return false;
		} else if (!citrus.equals(other.citrus))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (curedMeat == null) {
			if (other.curedMeat != null)
				return false;
		} else if (!curedMeat.equals(other.curedMeat))
			return false;
		if (driedFruit == null) {
			if (other.driedFruit != null)
				return false;
		} else if (!driedFruit.equals(other.driedFruit))
			return false;
		if (earthy == null) {
			if (other.earthy != null)
				return false;
		} else if (!earthy.equals(other.earthy))
			return false;
		if (floral == null) {
			if (other.floral != null)
				return false;
		} else if (!floral.equals(other.floral))
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (grape1 == null) {
			if (other.grape1 != null)
				return false;
		} else if (!grape1.equals(other.grape1))
			return false;
		if (grape2 == null) {
			if (other.grape2 != null)
				return false;
		} else if (!grape2.equals(other.grape2))
			return false;
		if (grape3 == null) {
			if (other.grape3 != null)
				return false;
		} else if (!grape3.equals(other.grape3))
			return false;
		if (img == null) {
			if (other.img != null)
				return false;
		} else if (!img.equals(other.img))
			return false;
		if (lamb == null) {
			if (other.lamb != null)
				return false;
		} else if (!lamb.equals(other.lamb))
			return false;
		if (matureAndHardCheese == null) {
			if (other.matureAndHardCheese != null)
				return false;
		} else if (!matureAndHardCheese.equals(other.matureAndHardCheese))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (oaky == null) {
			if (other.oaky != null)
				return false;
		} else if (!oaky.equals(other.oaky))
			return false;
		if (pasta == null) {
			if (other.pasta != null)
				return false;
		} else if (!pasta.equals(other.pasta))
			return false;
		if (pork == null) {
			if (other.pork != null)
				return false;
		} else if (!pork.equals(other.pork))
			return false;
		if (poultry == null) {
			if (other.poultry != null)
				return false;
		} else if (!poultry.equals(other.poultry))
			return false;
		if (price != other.price)
			return false;
		if (ratingAvg == null) {
			if (other.ratingAvg != null)
				return false;
		} else if (!ratingAvg.equals(other.ratingAvg))
			return false;
		if (ratingNum == null) {
			if (other.ratingNum != null)
				return false;
		} else if (!ratingNum.equals(other.ratingNum))
			return false;
		if (redFruit == null) {
			if (other.redFruit != null)
				return false;
		} else if (!redFruit.equals(other.redFruit))
			return false;
		if (region1 == null) {
			if (other.region1 != null)
				return false;
		} else if (!region1.equals(other.region1))
			return false;
		if (region2 == null) {
			if (other.region2 != null)
				return false;
		} else if (!region2.equals(other.region2))
			return false;
		if (region3 == null) {
			if (other.region3 != null)
				return false;
		} else if (!region3.equals(other.region3))
			return false;
		if (spices == null) {
			if (other.spices != null)
				return false;
		} else if (!spices.equals(other.spices))
			return false;
		if (sweet == null) {
			if (other.sweet != null)
				return false;
		} else if (!sweet.equals(other.sweet))
			return false;
		if (tannic == null) {
			if (other.tannic != null)
				return false;
		} else if (!tannic.equals(other.tannic))
			return false;
		if (treeFruit == null) {
			if (other.treeFruit != null)
				return false;
		} else if (!treeFruit.equals(other.treeFruit))
			return false;
		if (tropical == null) {
			if (other.tropical != null)
				return false;
		} else if (!tropical.equals(other.tropical))
			return false;
		if (veal == null) {
			if (other.veal != null)
				return false;
		} else if (!veal.equals(other.veal))
			return false;
		if (vegetal == null) {
			if (other.vegetal != null)
				return false;
		} else if (!vegetal.equals(other.vegetal))
			return false;
		if (wineStyle == null) {
			if (other.wineStyle != null)
				return false;
		} else if (!wineStyle.equals(other.wineStyle))
			return false;
		if (wineId != other.wineId)
			return false;
		if (winery == null) {
			if (other.winery != null)
				return false;
		} else if (!winery.equals(other.winery))
			return false;
		if (yeasty == null) {
			if (other.yeasty != null)
				return false;
		} else if (!yeasty.equals(other.yeasty))
			return false;
		return true;
	}

}

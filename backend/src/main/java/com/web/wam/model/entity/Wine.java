package com.web.wam.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "winery")
    private String winery;
    @Basic
    @Column(name = "grapes")
    private String grapes;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "regions")
    private String regions;
    @Basic
    @Column(name = "allergens")
    private String allergens;
    @Basic
    @Column(name = "cat1")
    private String cat1;
    @Basic
    @Column(name = "cat2")
    private String cat2;
    @Basic
    @Column(name = "rating_avg")
    private Double ratingAvg;
    @Basic
    @Column(name = "rating_num")
    private Integer ratingNum;
    @Basic
    @Column(name = "taste")
    private String taste;
    @Basic
    @Column(name = "acidic")
    private Double acidic;
    @Basic
    @Column(name = "alcohol_content")
    private Double alcoholContent;
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
    @Column(name = "style")
    private String style;
    @Basic
    @Column(name = "food_parings")
    private String foodParings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine that = (Wine) o;
        return wineId == that.wineId && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(winery, that.winery) && Objects.equals(grapes, that.grapes) && Objects.equals(country, that.country) && Objects.equals(regions, that.regions) && Objects.equals(allergens, that.allergens) && Objects.equals(cat1, that.cat1) && Objects.equals(cat2, that.cat2) && Objects.equals(ratingAvg, that.ratingAvg) && Objects.equals(ratingNum, that.ratingNum) && Objects.equals(taste, that.taste) && Objects.equals(acidic, that.acidic) && Objects.equals(alcoholContent, that.alcoholContent) && Objects.equals(bold, that.bold) && Objects.equals(tannic, that.tannic) && Objects.equals(sweet, that.sweet) && Objects.equals(style, that.style) && Objects.equals(foodParings, that.foodParings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wineId, name, price, winery, grapes, country, regions, allergens, cat1, cat2, ratingAvg, ratingNum, taste, acidic, alcoholContent, bold, tannic, sweet, style, foodParings);
    }
}

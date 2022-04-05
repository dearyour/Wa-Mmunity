package com.web.wam.model.dto.wine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("WineWishlistResponse")
public class WineWishlistResponse {

    @ApiModelProperty(name = "wishlist id", example = "123")
    private Integer id;

    @ApiModelProperty(name = "memberId", example = "123")
    private Integer memberId;

    @ApiModelProperty(name = "wineId", example = "123")
    private Integer wineId;
}

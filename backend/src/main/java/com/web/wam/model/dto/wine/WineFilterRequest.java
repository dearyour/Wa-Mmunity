package com.web.wam.model.dto.wine;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("WineReviewPutRequest")
public class WineFilterRequest {
	@ApiModelProperty(name = "와인 스타일", example = "1")
	List<String> wineStyle;

	@ApiModelProperty(name = "최소 가격", example = "1")
	int minPrice;

	@ApiModelProperty(name = "최대 가격", example = "1")
	int maxPrice;

	@ApiModelProperty(name = "최소 평점", example = "1")
	int minRate;

	@ApiModelProperty(name = "최대 평점", example = "1")
	int maxRate;

	@ApiModelProperty(name = "country", example = "1")
	List<String> country;

	@ApiModelProperty(name = "region", example = "1")
	List<String> region;
}

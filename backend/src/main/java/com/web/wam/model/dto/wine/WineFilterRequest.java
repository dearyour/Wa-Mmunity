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
	@ApiModelProperty(name = "게시글 ID", example = "1")
	List<String> wineStyle;

	@ApiModelProperty(name = "게시글 ID", example = "1")
	int minPrice;

	@ApiModelProperty(name = "게시글 ID", example = "1")
	int maxPrice;

	@ApiModelProperty(name = "게시글 ID", example = "1")
	int minRate;

	@ApiModelProperty(name = "게시글 ID", example = "1")
	int maxRate;

	@ApiModelProperty(name = "게시글 ID", example = "1")
	List<String> country;

	@ApiModelProperty(name = "게시글 ID", example = "1")
	List<String> region;
}

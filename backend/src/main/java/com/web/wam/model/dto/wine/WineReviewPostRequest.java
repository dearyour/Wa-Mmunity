package com.web.wam.model.dto.wine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("WineReviewRequest")
public class WineReviewPostRequest {
	@ApiModelProperty(name = "와인 Id", example = "5")
	int wineId;

	@ApiModelProperty(name = "회원 Id", example = "123")
	int memberId;

	@ApiModelProperty(name = "평점", example = "3.0")
	Double rating;

	@ApiModelProperty(name = "내용", example = "리뷰 내용")
	String content;
}

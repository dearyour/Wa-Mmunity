package com.web.wam.model.dto.wine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("WineReviewPutRequest")
public class WineReviewPutRequest {
	@ApiModelProperty(name = "게시글 ID", example = "1")
	int wineReviewId;

	@ApiModelProperty(name = "평점", example = "3")
	Double rating;

	@ApiModelProperty(name = "내용", example = "자유글 내용")
	String content;
}

package com.web.wam.model.dto.wine;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("WineReviewResponse")
public class WineReviewResponse {
	@ApiModelProperty(name = "reviewId", example = "123")
	private Integer id;

	@ApiModelProperty(name = "wineId", example = "123")
	private Integer wineId;

	@ApiModelProperty(name = "memberId", example = "123")
	private Integer memberId;

	@ApiModelProperty(name = "rating", example = "123")
	private Double rating;

	@ApiModelProperty(name = "content", example = "123")
	private String content;

	@ApiModelProperty(name = "regtime", example = "123")
	private LocalDateTime regtime;
}

package com.web.wam.model.dto.wine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("WineReviewFlaskResponse")
public class WineReviewFlaskResponse {
	@ApiModelProperty(name = "wine", example = "123")
	private Integer wine;

	@ApiModelProperty(name = "user", example = "123")
	private Integer user;

	@ApiModelProperty(name = "rating", example = "123")
	private Double rating;
}

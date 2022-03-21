package com.web.wam.model.dto.freeboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FreeboardLikePostRequest")
public class FreeboardLikePostRequest {
	@ApiModelProperty(name = "게시글", example = "0")
	int atricleId;
	
	@ApiModelProperty(name = "회원", example = "0")
	int memberId;
}


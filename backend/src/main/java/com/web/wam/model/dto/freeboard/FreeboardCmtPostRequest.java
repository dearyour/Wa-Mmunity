package com.web.wam.model.dto.freeboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FreeboardCmtPostRequest")
public class FreeboardCmtPostRequest {
	@ApiModelProperty(name = "게시글", example = "0")
	int atricleId;
	
	@ApiModelProperty(name = "작성자", example = "0")
	int memberId;
	

	@ApiModelProperty(name = "내용", example = "댓글 내용")
	String content;
	
}


package com.web.wam.model.dto.freeboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FreeboardCmtPutRequest")
public class FreeBoardCmtPutRequest {
	@ApiModelProperty(name = "댓글Id", example = "0")
	int commentId;
	
	@ApiModelProperty(name = "내용", example = "댓글 제목")
	String content;
}
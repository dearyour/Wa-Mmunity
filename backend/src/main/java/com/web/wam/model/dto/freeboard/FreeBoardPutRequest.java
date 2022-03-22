package com.web.wam.model.dto.freeboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FreeboardPutRequest")
public class FreeBoardPutRequest {
	@ApiModelProperty(name="게시글 ID", example = "1")
	int articleId;
	
	@ApiModelProperty(name = "제목", example = "자유글 제목")
	String title;

	@ApiModelProperty(name = "내용", example = "자유글 내용")
	String content;
	
	@ApiModelProperty(name = "사진", example = "자유글 사진첨부")
	String photo;
	
	@ApiModelProperty(name = "태그", example = "자유글 카테고리")
	String tag;
}
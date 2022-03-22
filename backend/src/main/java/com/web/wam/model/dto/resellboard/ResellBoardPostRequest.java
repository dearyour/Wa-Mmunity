package com.web.wam.model.dto.resellboard;


import com.web.wam.model.entity.resellboard.ResellBoard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Data
@ApiModel("ResellBoardPostRequest")
public class ResellBoardPostRequest {

	@ApiModelProperty(name = "작성자", example = "0")
	int memberId;
	
	@ApiModelProperty(name = "제목", example = "리셀게시물 제목")
	String title;

	@ApiModelProperty(name = "내용", example = "리셀게시물 내용")
	String content;

	// photo file is needed to register on AWS S3 and set path in ResellBoard.photo(String)
	@ApiModelProperty(name = "사진", example = "리셀게시물 사진첨부")
	File photo;
	
	@ApiModelProperty(name = "태그", example = "리셀게시물 카테고리")
	String tag;

	@ApiModelProperty(name = "가격", example = "리셀게시물 가격")
	Integer price;

	public ResellBoard toEntity() {
		return ResellBoard.builder()
				.memberId(this.memberId)
				.title(this.title)
				.content(this.content)
				.tag(this.tag)
				.price(this.price)
				.build();
	}
}


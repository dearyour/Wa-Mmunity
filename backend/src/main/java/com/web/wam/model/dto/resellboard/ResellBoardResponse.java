package com.web.wam.model.dto.resellboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("ResellBoardResponse")
public class ResellBoardResponse {

    @ApiModelProperty(name = "게시물 ID", example = "0")
    Integer id;

    @ApiModelProperty(name = "작성자", example = "0")
    int memberId;

    @ApiModelProperty(name = "제목", example = "리셀게시물 제목")
    String title;

    @ApiModelProperty(name = "내용", example = "리셀게시물 내용")
    String content;

    @ApiModelProperty(name = "사진", example = "리셀게시물 사진첨부")
    String photo;

    @ApiModelProperty(name = "태그", example = "리셀게시물 카테고리")
    String tag;

    @ApiModelProperty(name = "가격", example = "리셀게시물 가격")
    String price;

    @ApiModelProperty(name = "작성 시간", example = "리셀게시물이 작성된 시간")
    LocalDateTime regtime;
}

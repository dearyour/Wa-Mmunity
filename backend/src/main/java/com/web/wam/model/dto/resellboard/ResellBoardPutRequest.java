package com.web.wam.model.dto.resellboard;

import com.web.wam.model.entity.resellboard.ResellBoard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("ResellBoardPutRequest")
public class ResellBoardPutRequest {

    @ApiModelProperty(name="회원 ID", example = "1")
    Integer memberId;

    @ApiModelProperty(name = "제목", example = "자유글 제목")
    String title;

    @ApiModelProperty(name = "내용", example = "자유글 내용")
    String content;

    @ApiModelProperty(name = "사진", example = "자유글 사진첨부")
    String tag;

    @ApiModelProperty(name = "태그", example = "자유글 카테고리")
    Integer price;

    @ApiModelProperty(name="게시글 ID", example = "1")
    Integer id;

    public ResellBoard toEntity() {
        return ResellBoard.builder()
                .memberId(this.memberId)
                .title(this.title)
                .content(this.content)
                .tag(this.tag)
                .price(this.price)
                .id(this.id)
                .build();
    }
}

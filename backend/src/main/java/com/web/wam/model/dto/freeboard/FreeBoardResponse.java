package com.web.wam.model.dto.freeboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("FreeBoardResponse")
public class FreeBoardResponse implements Serializable {

    @ApiModelProperty(name = "FreeBoard articleId", example = "123")
    private Integer articleId;

    @ApiModelProperty(name = "FreeBoard memberId", example = "123")
    private Integer memberId;

    @ApiModelProperty(name = "FreeBoard title", example = "게시물입니다")
    private String title;

    @ApiModelProperty(name = "FreeBoard content", example = "내용입니다.")
    private String content;

    @ApiModelProperty(name = "FreeBoard photo", example = "S3 경로")
    private String photo;

    @ApiModelProperty(name = "FreeBoard tag", example = "태그 이름")
    private String tag;

    @ApiModelProperty(name = "FreeBoard registered time", example = "2022-11-11")
    private LocalDateTime regtime;

    @ApiModelProperty(name = "FreeBoard likeCnt", example = "22")
    private Long likeCnt;
}
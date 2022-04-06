package com.web.wam.model.dto.resellboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("ResellBoardCmtResponse")
public class ResellBoardCmtResponse {

    @ApiModelProperty(name = "ResellBoardCmt id", example = "123")
    Integer id;

    @ApiModelProperty(name = "ResellBoardCmt articleId", example = "123")
    Integer articleId;

    @ApiModelProperty(name = "ResellBoardCmt memberId", example = "123")
    Integer memberId;

    @ApiModelProperty(name = "ResellBoardCmt content", example = "123")
    String content;

    @ApiModelProperty(name = "ResellBoardCmt member nickname", example = "123")
    String nickname;

    @ApiModelProperty(name = "ResellBoardCmt regtime", example = "123")
    LocalDateTime regtime;

}

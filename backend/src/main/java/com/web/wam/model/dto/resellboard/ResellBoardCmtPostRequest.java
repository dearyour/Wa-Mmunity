package com.web.wam.model.dto.resellboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("ResellBoardCmtPostRequest")
public class ResellBoardCmtPostRequest{

    @ApiModelProperty(name = "게시글", example = "0")
    Integer articleId;

    @ApiModelProperty(name = "작성자", example = "0")
    Integer memberId;

    @ApiModelProperty(name = "내용", example = "0")
    String content;
}

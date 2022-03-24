package com.web.wam.model.dto.resellboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("ResellArticleLikePostRequest")
public class ResellArticleLikePostRequest {

    @ApiModelProperty(name = "게시글 ID", example = "0")
    Integer articleId;

    @ApiModelProperty(name = "회원 ID", example = "0")
    Integer memberId;
}

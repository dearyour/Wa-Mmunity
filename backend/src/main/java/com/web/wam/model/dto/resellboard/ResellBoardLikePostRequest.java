package com.web.wam.model.dto.resellboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("ResellBoardLikePostRequest")
public class ResellBoardLikePostRequest {

    @ApiModelProperty(name = "게시글", example = "0")
    @NotNull
    Integer articleId;

    @ApiModelProperty(name = "회원", example = "0")
    @NotNull
    Integer memberId;
}

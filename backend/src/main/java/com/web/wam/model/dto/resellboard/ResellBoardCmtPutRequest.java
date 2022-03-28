package com.web.wam.model.dto.resellboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("ResellBoardCmtPutRequest")
public class ResellBoardCmtPutRequest {

    @ApiModelProperty(name = "댓글 ID", example = "0")
    @NotNull
    Integer id;

    @ApiModelProperty(name = "댓글 내용", example = "댓글 내용입니다.")
    String content;
}

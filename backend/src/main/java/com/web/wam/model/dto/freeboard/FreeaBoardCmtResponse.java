package com.web.wam.model.dto.freeboard;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("FreeaBoardCmtResponse")
public class FreeaBoardCmtResponse implements Serializable {
	@ApiModelProperty(name = "FreeBoardCmt commentId", example = "123")
	private Integer commentId;

	@ApiModelProperty(name = "FreeBoardCmt memberId", example = "123")
	private Integer memberId;

	@ApiModelProperty(name = "FreeBoardCmt memberNickName", example = "123")
	private String memberNickName;

	@ApiModelProperty(name = "FreeBoardCmt content", example = "내용입니다.")
	private String content;

	@ApiModelProperty(name = "FreeBoard registered time", example = "2022-11-11")
	private LocalDateTime regtime;

}

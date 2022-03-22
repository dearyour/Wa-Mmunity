package com.web.wam.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BaseResponse")
public class BaseResponse {

	@ApiModelProperty(value = "status")
	public Integer status;
	@ApiModelProperty(value = "object")
	public Object object;

    public BaseResponse() {
    }

	public BaseResponse(Integer status) {
		this.status = status;
	}

	public BaseResponse(Integer status, Object object) {
		this.status = status;
		this.object = object;
	}

	public static BaseResponse of(Integer status, Object object) {
		BaseResponse body = new BaseResponse();
		body.status = status;
		body.object = object;
		return body;
	}
}

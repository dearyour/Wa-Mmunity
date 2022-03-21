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
    public int status;
    @ApiModelProperty(value = "object")
    public Object object;

    public BaseResponse() {
    }

    public BaseResponse(int status) {
        this.status = status;
    }

    public BaseResponse(int status, Object object) {
        this.status = status;
        this.object = object; 
    }

    public static BaseResponse of(int status, Object object) {
    	BaseResponse body = new BaseResponse();
        body.status = status;
        body.object = object;
        return body;
    }
}
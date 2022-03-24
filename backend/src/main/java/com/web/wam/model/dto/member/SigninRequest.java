package com.web.wam.model.dto.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class SigninRequest {

    @ApiModelProperty(value = "email", required = true)
    private String email;

    @ApiModelProperty(value = "password", required = true)
    private String password;

}

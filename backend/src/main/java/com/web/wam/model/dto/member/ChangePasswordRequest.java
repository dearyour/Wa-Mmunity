package com.web.wam.model.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordRequest {

    @ApiModelProperty(value = "email", required = true)
    private String email;

    @ApiModelProperty(value = "existing password", required = true)
    @NotBlank
    private String password;

    @ApiModelProperty(value = "new password", required = true)
    @NotBlank
    private String newpassword;
}

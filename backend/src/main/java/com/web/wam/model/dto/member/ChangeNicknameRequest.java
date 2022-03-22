package com.web.wam.model.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ChangeNicknameRequest {

    @ApiModelProperty(value = "email", required = true)
    @NotBlank
    private String email;

    @ApiModelProperty(value = "expected nickname", required = true)
    @NotBlank
    private String nickname;
}

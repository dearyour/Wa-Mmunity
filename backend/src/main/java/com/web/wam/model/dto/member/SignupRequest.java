package com.web.wam.model.dto.member;

import com.web.wam.model.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SignupRequest implements Serializable {

    @ApiModelProperty(required = true)
    @NotBlank
    private final String nickname;

    @ApiModelProperty(required = true)
    @NotBlank
    @Email
    private final String email;

    @ApiModelProperty(required = true)
    @NotBlank
    private final String password;

    private final Integer isAdult;

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .isAdult(this.isAdult)
                .build();
    }
}

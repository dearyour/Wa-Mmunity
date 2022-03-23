package com.web.wam.model.dto.member;

import com.web.wam.model.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SignupRequest {

    @ApiModelProperty(required = true)
    @NotBlank
    private String nickname;

    @ApiModelProperty(required = true)
    @NotBlank
    @Email
    private String email;

    @ApiModelProperty(required = true)
    @NotBlank
    private String password;

    private Integer isAdult;

    protected SignupRequest() {
        
    }

    public SignupRequest(String nickname, String email, String password, Integer isAdult) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.isAdult = isAdult;
    }

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .isAdult(this.isAdult)
                .build();
    }
}

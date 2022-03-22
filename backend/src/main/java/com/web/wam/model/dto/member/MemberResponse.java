package com.web.wam.model.dto.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ApiModel("MemberResponse")
public class MemberResponse {

    @ApiModelProperty(value = "Member ID", required = false)
    private final Integer id;

    @ApiModelProperty(value = "Member Nickname", required = true)
    private final String nickname;

    @ApiModelProperty(value = "Member Email", required = true)
    private final String email;

    @ApiModelProperty(value = "Member IsAdult", required = false)
    private final Integer isAdult;

    @ApiModelProperty(value = "Member Regtime", required = false)
    private final LocalDateTime regtime;
}

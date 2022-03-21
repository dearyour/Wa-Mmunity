package com.web.wam.model.dto.member;

import lombok.Data;

import java.io.Serializable;

@Data
public class SigninRequest implements Serializable {
    private final String email;
    private final String password;
}

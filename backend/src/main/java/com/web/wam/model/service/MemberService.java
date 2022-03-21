package com.web.wam.model.service;

import com.web.wam.model.dto.member.SigninRequest;
import com.web.wam.model.dto.member.SignupRequest;

public interface MemberService {

    void signup(SignupRequest signupRequest);

    String signin(SigninRequest request);
}

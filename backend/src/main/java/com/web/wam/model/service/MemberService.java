package com.web.wam.model.service;

import com.web.wam.model.dto.member.*;

public interface MemberService {

    void signup(SignupRequest signupRequest);

    String signin(SigninRequest request);

    boolean idcheck(String email);

    String createNewPassword(String email);

    void changePassword(ChangePasswordRequest request);

    MemberResponse findMemberInfo(String email);

    void updateMemberNickname(ChangeNicknameRequest request);
}

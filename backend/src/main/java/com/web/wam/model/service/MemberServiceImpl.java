package com.web.wam.model.service;

import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void signup(SignupRequest signupRequest) {
        
    }
}

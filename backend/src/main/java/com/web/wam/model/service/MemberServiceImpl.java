package com.web.wam.model.service;

import com.web.wam.config.security.JwtTokenProvider;
import com.web.wam.exception.member.AlreadyExistEmailException;
import com.web.wam.exception.member.AlreadyExistNicknameException;
import com.web.wam.model.dto.member.SigninRequest;
import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.entity.Member;
import com.web.wam.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public void signup(SignupRequest signupRequest) {
        if(memberRepository.existsByEmail(signupRequest.getEmail())) {
            throw new AlreadyExistEmailException();
        } else if(memberRepository.existsByNickname(signupRequest.getNickname())) {
            throw new AlreadyExistNicknameException();
        } else {
            Member member = signupRequest.toEntity();
            member.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            memberRepository.save(member);
        }
    }

    @Override
    public String signin(SigninRequest request) {

        Member member = memberRepository.findByEmail(request.getEmail());

        // ID Verification
        if(member == null) throw new IllegalArgumentException("가입되지 않은 E-MAIL입니다.");

        // Password Verification
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");

        // return Token
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}

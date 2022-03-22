package com.web.wam.model.service;

import com.web.wam.config.security.JwtTokenProvider;
import com.web.wam.exception.member.AlreadyExistEmailException;
import com.web.wam.exception.member.AlreadyExistNicknameException;
import com.web.wam.exception.member.NotFoundMemberException;
import com.web.wam.model.dto.member.ChangePasswordRequest;
import com.web.wam.model.dto.member.SigninRequest;
import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.entity.Member;
import com.web.wam.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private final int TEMP_PASSWORD_SIZE = 10;

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

    @Override
    public boolean idcheck(String email) {
        return !memberRepository.existsByEmail(email);
    }

    @Override
    public String createNewPassword(String email) {

        String newPassword = "";

        if(memberRepository.existsByEmail(email)) {
            newPassword = getRandomPassword(TEMP_PASSWORD_SIZE);

            Member member = memberRepository.findByEmail(email);
            member.setPassword(passwordEncoder.encode(newPassword));
            memberRepository.save(member);
        } else {
            throw new NotFoundMemberException();
        }

        return newPassword;
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {

        Member member = memberRepository.findByEmail(request.getEmail());

        // ID Verification
        if(member == null) throw new IllegalArgumentException("가입되지 않은 E-MAIL입니다.");

        // Password Verification
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");

        member.setPassword(passwordEncoder.encode(request.getNewpassword()));
        memberRepository.save(member);
    }

    private String getRandomPassword(int size) {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&'
        };

        StringBuilder sb = new StringBuilder();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i = 0; i < size; i++) {
            idx = sr.nextInt(len);
            sb.append(charSet[idx]);
        }
        return sb.toString();
    }
}

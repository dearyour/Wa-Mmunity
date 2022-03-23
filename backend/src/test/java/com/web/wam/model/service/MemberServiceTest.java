package com.web.wam.model.service;

import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.entity.Member;
import com.web.wam.model.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService = new MemberServiceImpl();

    @Test
    @DisplayName("test signup function")
    void signup() {

        //given
        SignupRequest signupRequest = new SignupRequest("nickname", "email@gmail.com", "signup123123", 1);
        when(memberRepository.save(any())).thenReturn(signupRequest.toEntity());
        SignupRequest signupRequest2 = new SignupRequest("nickname2", "email2@gmail.com", "signup2123123", 1);
        when(memberRepository.save(any())).thenReturn(signupRequest2.toEntity());

        //when
        memberService.signup(signupRequest);
        memberService.signup(signupRequest2);

        //then


    }
}

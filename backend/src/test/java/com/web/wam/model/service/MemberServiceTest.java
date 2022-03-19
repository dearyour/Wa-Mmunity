package com.web.wam.model.service;

import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("test signup function")
    void signup() {

        //given
        SignupRequest signupRequest = new SignupRequest("nickname", "email@gmail.com", "signup123123", 1);
        SignupRequest signupRequest2 = new SignupRequest("nickname2", "email2@gmail.com", "signup2123123", 1);

        //when
        memberService.signup(signupRequest);
        memberService.signup(signupRequest2);

        //then
        // Id 생성 전략을 Identity를 사용하므로, 실제 DBd에 저장되야만 Id가 생성된다. 따라서 테스트에서 Id를 검증할 수 없다.
        // 만약 Id를 검증하려면 Repository를 Mock이 아니라 실제 Bean으로 사용해야 가능할 듯 싶다.
    }
}

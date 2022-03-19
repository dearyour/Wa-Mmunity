package com.web.wam.model.repository;

import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Save Member at Database")
    void saveMember() {
        //given
        Member member = new SignupRequest("nickname", "email@gmail.com", "wammunity123456", 1).toEntity();
        //when
        Member savedMember = memberRepository.save(member);
        //then
        Assertions.assertThat(member).isSameAs(savedMember);
        Assertions.assertThat(member.getNickname()).isSameAs(savedMember.getNickname());
        Assertions.assertThat(member.getEmail()).isSameAs(savedMember.getEmail());
        Assertions.assertThat(member.getPassword()).isSameAs(savedMember.getPassword());
        Assertions.assertThat(member.getIsAdult()).isSameAs(savedMember.getIsAdult());
    }
    @Test
    @DisplayName("Save Member at Database")
    void saveMember2() {
        //given
        Member member = new SignupRequest("nickname", "email@gmail.com", "wammunity123456", 1).toEntity();
        //when
        Member savedMember = memberRepository.save(member);
        //then
        Assertions.assertThat(member).isSameAs(savedMember);
        Assertions.assertThat(member.getNickname()).isSameAs(savedMember.getNickname());
        Assertions.assertThat(member.getEmail()).isSameAs(savedMember.getEmail());
        Assertions.assertThat(member.getPassword()).isSameAs(savedMember.getPassword());
        Assertions.assertThat(member.getIsAdult()).isSameAs(savedMember.getIsAdult());
    }

    @Test
    @DisplayName("ExistsByEmail")
    void exsitsByEmail() {
        //given
        Member member = new SignupRequest("nickname", "email@gmail.com", "wammunity123456", 1).toEntity();
        //when
        memberRepository.save(member);
        boolean isExists1 = memberRepository.existsByEmail(member.getEmail());
        boolean isExists2 = memberRepository.existsByEmail(member.getNickname());
        //then
        Assertions.assertThat(isExists1).isSameAs(true);
        Assertions.assertThat(isExists2).isSameAs(false);
    }
}

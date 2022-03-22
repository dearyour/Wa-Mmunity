package com.web.wam.model.repository;

import com.web.wam.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Member findByEmail(String email);
}

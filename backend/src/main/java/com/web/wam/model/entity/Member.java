package com.web.wam.model.entity;

import lombok.*;
<<<<<<< HEAD

import javax.persistence.*;
=======
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
>>>>>>> b4b9e52929aeeff9195938f468e3e051f65fff79
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Integer id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_adult", nullable = false)
    private Integer isAdult;

    @Column(name = "regtime", nullable = false)
    private LocalDateTime regtime;

}
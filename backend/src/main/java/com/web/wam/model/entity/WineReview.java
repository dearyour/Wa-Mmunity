package com.web.wam.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wine_review")
public class WineReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wine_review_id", nullable = false)
    private Integer id;

    @Column(name = "wine_id", nullable = false)
    private Integer wineId;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "rating")
    private String rating;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "regtime")
    private String regtime;

}
package com.web.wam.model.entity.resellboard;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resell_article_like")
public class ResellArticleLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resell_like_id", nullable = false)
    private Integer id;

    @Column(name = "article_id", nullable = false)
    private Integer articleId;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;
}
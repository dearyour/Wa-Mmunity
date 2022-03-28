package com.web.wam.model.entity.resellboard;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resell_article_comment")
public class ResellArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    Integer id;

    @Column(name = "article_id", nullable = false)
    Integer articleId;

    @Column(name = "member_id", nullable = false)
    Integer memberId;

    @Lob
    @Column(name = "content", nullable = false)
    String content;

    @Column(name = "regtime", nullable = false)
    LocalDateTime regtime;
}
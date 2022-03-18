package com.web.wam.model.entity.freeboard;

import lombok.*;

import javax.persistence.*;
<<<<<<< HEAD
=======
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
@Table(name = "free_article_comment")
public class FreeArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Integer id;

    @Column(name = "article_id", nullable = false)
    private Integer articleId;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "regtime", nullable = false)
    private LocalDateTime regtime;
}
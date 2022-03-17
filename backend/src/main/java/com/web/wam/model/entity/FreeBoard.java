package com.web.wam.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "free_board")
public class FreeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "article_id", nullable = false)
    private Integer articleId;

    //@Column(name = "member_id", nullable = false)
    private Integer memberId;

    //@Column(name = "title", nullable = false)
    private String title;

    //@Lob
    //@Column(name = "content", nullable = false)
    private String content;

    //@Lob
    //@Column(name = "photo")
    private String photo;

    //@Lob
    //@Column(name = "tag")
    private String tag;

    @CreatedDate
    //@Column(name = "regtime", nullable = false)
    private Instant regtime;

}
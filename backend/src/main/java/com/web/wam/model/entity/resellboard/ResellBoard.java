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
@Table(name = "resell_board")
public class ResellBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", nullable = false)
    private Integer id;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Lob
    @Column(name = "photo")
    private String photo;

    @Lob
    @Column(name = "tag")
    private String tag;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "regtime", nullable = false)
    private LocalDateTime regtime;

}
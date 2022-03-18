package com.web.wam.model.entity.freeboard;

import lombok.*;
<<<<<<< HEAD
=======
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
>>>>>>> b4b9e52929aeeff9195938f468e3e051f65fff79


import javax.persistence.*;
<<<<<<< HEAD
=======

>>>>>>> b4b9e52929aeeff9195938f468e3e051f65fff79
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "free_board")
public class FreeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", nullable = false)
    private Integer articleId;

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

    @Column(name = "regtime", nullable = false)
    private LocalDateTime regtime;

}
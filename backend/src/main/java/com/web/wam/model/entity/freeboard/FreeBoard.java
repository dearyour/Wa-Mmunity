package com.web.wam.model.entity.freeboard;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
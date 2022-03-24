package com.web.wam.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Double rating;

	@Column(name = "content")
	private String content;

	@Column(name = "regtime")
	private LocalDateTime regtime;

}
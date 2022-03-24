package com.web.wam.model.entity;

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
@Table(name = "wine_survey")
public class WineSurvey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wine_survey_id", nullable = false)
	private Integer id;

	@Column(name = "member_id", nullable = false)
	int memberId;

	@Column(name = "amount_of_alcohol", nullable = false)
	int amountOfAlcohol;

	@Column(name = "smell_taste1", nullable = false)
	int smellTaste1;

	@Column(name = "smell_taste2", nullable = false)
	int smellTaste2;

	@Column(name = "smell_taste3", nullable = false)
	int smellTaste3;

	@Column(name = "acidic_preference", nullable = false)
	int acidicPreference;

	@Column(name = "sweet_preference", nullable = false)
	int sweetPreference;

	@Column(name = "tannic_preference", nullable = false)
	int tannicPreference;

	@Column(name = "bold_preference", nullable = false)
	int boldPreference;

	@Column(name = "min_price", nullable = false)
	int minPrice;

	@Column(name = "max_price", nullable = false)
	int maxPrice;

	@Column(name = "food1", nullable = false)
	int food1;

	@Column(name = "food2", nullable = false)
	int food2;

	@Column(name = "food3", nullable = false)
	int food3;
}

package com.web.wam.model.dto.wine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("WineSurveyRequest")
public class WineSurveyRequest {
	@ApiModelProperty(name = "회원 Id", example = "123")
	int memberId;

	@ApiModelProperty(name = "주량 체크", example = "1")
	int amountOfAlcohol;

	@ApiModelProperty(name = "향 1", example = "1")
	int smellTaste1;

	@ApiModelProperty(name = "향 2", example = "2")
	int smellTaste2;

	@ApiModelProperty(name = "향 3", example = "3")
	int smellTaste3;

	@ApiModelProperty(name = "신맛 선호도", example = "1")
	int acidicPreference;

	@ApiModelProperty(name = "단맛 선호도", example = "1")
	int sweetPreference;

	@ApiModelProperty(name = "떫음 선호도", example = "1")
	int tannicPreference;

	@ApiModelProperty(name = "바디감 선호도", example = "1")
	int boldPreference;

	@ApiModelProperty(name = "최소 가격", example = "100")
	int minPrice;

	@ApiModelProperty(name = "최대 가격", example = "50000")
	int maxPrice;

	@ApiModelProperty(name = "음식 1", example = "1")
	int food1;

	@ApiModelProperty(name = "음식 2", example = "2")
	int food2;

	@ApiModelProperty(name = "음식 3", example = "3")
	int food3;
}

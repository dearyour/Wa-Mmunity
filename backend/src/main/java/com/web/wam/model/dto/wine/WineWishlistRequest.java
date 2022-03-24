package com.web.wam.model.dto.wine;

import javax.validation.constraints.NotBlank;

import com.web.wam.model.entity.WineWishlist;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("WineWishlistRequest")
public class WineWishlistRequest {
	@ApiModelProperty(name = "와인 Id", example = "0", required = true)
	@NotBlank
	int wineId;

	@ApiModelProperty(name = "회원 Id", example = "0", required = true)
	@NotBlank
	int memberId;

	protected WineWishlistRequest() {

	}

	public WineWishlistRequest(int wineId, int memberId) {
		this.wineId = wineId;
		this.memberId = memberId;
	}

	public WineWishlist toEntity() {
		return WineWishlist.builder().wineId(this.wineId).memberId(this.memberId).build();
	}
}
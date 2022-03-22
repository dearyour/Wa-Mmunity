package com.web.wam.model.dto.freeboard;

import java.util.List;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.entity.freeboard.FreeArticleComment;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FreeboardCmtGetResponse")
public class FreeBoardCmtGetResponse extends BaseResponse {
	List<FreeArticleComment> comments = null;

	public static FreeBoardCmtGetResponse of(Integer status, List<FreeArticleComment> comments) {
		FreeBoardCmtGetResponse res = new FreeBoardCmtGetResponse();
		res.setStatus(status);
		res.setComments(comments);
		return res;
	}
}

package com.web.wam.model.dto.freeboard;

import java.util.List;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.entity.freeboard.FreeArticleComment;
import com.web.wam.model.entity.freeboard.FreeBoard;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("FreeboardCmtGetResponse")
public class FreeboardCmtGetResponse extends BaseResponse {
	List<FreeArticleComment> comments = null;
	
	public static FreeboardCmtGetResponse of(Integer statusCode, String message, List<FreeArticleComment> comments) {
		FreeboardCmtGetResponse res = new FreeboardCmtGetResponse();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setComments(comments);
		return res;
	}
}
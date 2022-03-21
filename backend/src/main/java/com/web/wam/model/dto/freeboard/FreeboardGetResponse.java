package com.web.wam.model.dto.freeboard;

import java.util.List;
import java.util.Map;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.entity.freeboard.FreeBoard;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FreeboardGetResponse")
public class FreeboardGetResponse extends BaseResponse {
	Map<FreeBoard,Long> articleList = null;
	
	public static FreeboardGetResponse of(Integer statusCode, String message, Map<FreeBoard,Long> articleList) {
		FreeboardGetResponse res = new FreeboardGetResponse();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setArticleList(articleList);
		return res;
	}
}

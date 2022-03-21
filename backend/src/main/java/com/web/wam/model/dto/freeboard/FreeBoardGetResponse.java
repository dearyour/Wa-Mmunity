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
public class FreeBoardGetResponse extends BaseResponse {
	List<FreeBoardResponse> articleList = null;
	
	public static FreeBoardGetResponse of(Integer status, List<FreeBoardResponse> articleList) {
		FreeBoardGetResponse res = new FreeBoardGetResponse();
		res.setStatus(status);
		res.setArticleList(articleList);
		return res;
	}
}

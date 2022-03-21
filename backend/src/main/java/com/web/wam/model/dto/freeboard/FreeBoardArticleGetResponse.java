package com.web.wam.model.dto.freeboard;

import java.util.List;

import com.web.wam.model.dto.BaseResponse;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FreeBoardArticleGetResponse")
public class FreeBoardArticleGetResponse extends BaseResponse {
	FreeBoardResponse article;
	List<FreeaBoardCmtResponse> comments;

	public static FreeBoardArticleGetResponse of(Integer status, FreeBoardResponse article,
			List<FreeaBoardCmtResponse> comments) {
		FreeBoardArticleGetResponse res = new FreeBoardArticleGetResponse();
		res.setStatus(status);
		res.setArticle(article);
		res.setComments(comments);
		return res;
	}
}

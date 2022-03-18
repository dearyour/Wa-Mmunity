package com.web.wam.model.dto.freeboard;

import java.util.List;
import java.util.Optional;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.entity.freeboard.FreeArticleComment;
import com.web.wam.model.entity.freeboard.FreeBoard;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("FreeBoardArticleGetResponse")
public class FreeBoardArticleGetResponse extends BaseResponse {
	Optional<FreeBoard> article = null;
	List<FreeArticleComment> comments = null;
	
	public static FreeBoardArticleGetResponse of(Integer statusCode, String message, Optional<FreeBoard> article, List<FreeArticleComment> comments) {
		FreeBoardArticleGetResponse res = new FreeBoardArticleGetResponse();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setArticle(article);
		res.setComments(comments);
		return res;
	}
}

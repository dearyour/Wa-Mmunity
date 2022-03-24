package com.web.wam.model.dto.freeboard;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FreeBoardArticleGetResponse")
public class FreeBoardArticleGetResponse {
	FreeBoardResponse article;
	List<FreeaBoardCmtResponse> comments;

	public FreeBoardArticleGetResponse(FreeBoardResponse article, List<FreeaBoardCmtResponse> comments) {
		super();
		this.article = article;
		this.comments = comments;
	}

}

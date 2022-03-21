package com.web.wam.model.service;

import java.util.List;

import com.web.wam.model.dto.freeboard.FreeBoardCmtPostRequest;
import com.web.wam.model.dto.freeboard.FreeBoardCmtPutRequest;
import com.web.wam.model.dto.freeboard.FreeBoardLikePostRequest;
import com.web.wam.model.dto.freeboard.FreeBoardPostRequest;
import com.web.wam.model.dto.freeboard.FreeBoardPutRequest;
import com.web.wam.model.dto.freeboard.FreeBoardResponse;
import com.web.wam.model.dto.freeboard.FreeaBoardCmtResponse;

public interface FreeBoardService {

	List<FreeBoardResponse> getAllArticle();

	void createArticle(FreeBoardPostRequest articleCreateInfo);

	void updateArticle(FreeBoardPutRequest articleUpdateInfo);

	void deleteArticle(int articleId);

	FreeBoardResponse getArticleById(int articleId);

	List<FreeaBoardCmtResponse> getCommentsById(int articleId);

	List<FreeBoardResponse> getArticleByMemberId(int memberId);

	List<FreeaBoardCmtResponse> getCommentByMemberId(int memberId);

	void createComment(FreeBoardCmtPostRequest commentCreateInfo);

	void updateComment(FreeBoardCmtPutRequest commentUpdateInfo);

	void deleteComment(int commentId);

	void addLike(FreeBoardLikePostRequest likeAddInfo);

	void cancelLike(FreeBoardLikePostRequest likeCancelInfo);

	long getLikeCountById(int articleId);

	List<FreeBoardResponse> getArticleByKeyword(String keyword);

}

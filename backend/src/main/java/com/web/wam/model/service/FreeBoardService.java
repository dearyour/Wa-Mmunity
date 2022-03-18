package com.web.wam.model.service;

import java.util.List;
import java.util.Optional;

import com.web.wam.model.dto.freeboard.FreeboardCmtPostRequest;
import com.web.wam.model.dto.freeboard.FreeboardCmtPutRequest;
import com.web.wam.model.dto.freeboard.FreeboardPostRequest;
import com.web.wam.model.dto.freeboard.FreeboardPutRequest;
import com.web.wam.model.entity.freeboard.FreeArticleComment;
import com.web.wam.model.entity.freeboard.FreeBoard;

public interface FreeBoardService {

	List<FreeBoard> getAllArticle();

	void createArticle(FreeboardPostRequest articleCreateInfo);

	void updateArticle(FreeboardPutRequest articleUpdateInfo);

	void deleteArticle(int articleId);

	Optional<FreeBoard> getArticleById(int articleId);

	List<FreeArticleComment> getCommentsById(int articleId);

	List<FreeBoard> getArticleByMemberId(int memberId);

	List<FreeArticleComment> getCommentByMemberId(int memberId);

	void createComment(FreeboardCmtPostRequest commentCreateInfo);

	void updateComment(FreeboardCmtPutRequest commentUpdateInfo);

	void deleteComment(int commentId);

}

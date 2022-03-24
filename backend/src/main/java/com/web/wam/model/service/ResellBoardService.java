package com.web.wam.model.service;

import com.web.wam.model.dto.resellboard.ResellBoardCmtResponse;
import com.web.wam.model.dto.resellboard.ResellBoardPostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardPutRequest;
import com.web.wam.model.dto.resellboard.ResellBoardResponse;

import java.util.List;

public interface ResellBoardService {

    List<ResellBoardResponse> getAllArticle();

    void createArticle(ResellBoardPostRequest articleCreateInfo, String photoPath);

    void updateArticle(ResellBoardPutRequest request, String newPhoto);

    public long getLikeCountById(Integer articleId);

    void deleteArticle(Integer articleId);

    ResellBoardResponse getArticleById(Integer articleId);

    List<ResellBoardCmtResponse> getCommentsById(Integer articleId);
}

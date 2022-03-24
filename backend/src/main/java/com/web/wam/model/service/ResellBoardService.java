package com.web.wam.model.service;

import com.web.wam.model.dto.resellboard.ResellBoardPostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardResponse;

import java.util.List;

public interface ResellBoardService {

    List<ResellBoardResponse> getAllArticle();

    void createArticle(ResellBoardPostRequest articleCreateInfo, String photoPath);

    public long getLikeCountById(Integer articleId);
}

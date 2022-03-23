package com.web.wam.model.service;

import com.web.wam.model.dto.resellboard.ResellBoardPostRequest;

public interface ResellBoardService {

    void createArticle(ResellBoardPostRequest articleCreateInfo, String photoPath);
}

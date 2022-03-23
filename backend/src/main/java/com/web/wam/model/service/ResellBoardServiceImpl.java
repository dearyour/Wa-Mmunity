package com.web.wam.model.service;

import com.web.wam.model.dto.resellboard.ResellBoardPostRequest;
import com.web.wam.model.entity.freeboard.FreeBoard;
import com.web.wam.model.entity.resellboard.ResellBoard;
import com.web.wam.model.repository.resellboard.ResellBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("resellBoardService")
public class ResellBoardServiceImpl implements ResellBoardService {

    @Autowired
    private ResellBoardRepository resellBoardRepository;

    @Override
    public void createArticle(ResellBoardPostRequest request, String photoPath) {
        ResellBoard article = request.toEntity();
        article.setPhoto(photoPath);
        article.setRegtime(LocalDateTime.now());
        article.setRegtime(LocalDateTime.now());
        resellBoardRepository.save(article);
    }
}

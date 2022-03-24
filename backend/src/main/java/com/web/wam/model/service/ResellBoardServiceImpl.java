package com.web.wam.model.service;

import com.web.wam.model.dto.resellboard.ResellBoardPostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardResponse;
import com.web.wam.model.entity.resellboard.ResellBoard;
import com.web.wam.model.repository.resellboard.ResellArticleLikeRepository;
import com.web.wam.model.repository.resellboard.ResellArticleLikeRepositorySupport;
import com.web.wam.model.repository.resellboard.ResellBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service("resellBoardService")
public class ResellBoardServiceImpl implements ResellBoardService {

    @Autowired
    private ResellBoardRepository resellBoardRepository;

    @Autowired
    private ResellArticleLikeRepository resellArticleLikeRepository;

    @Autowired
    private ResellArticleLikeRepositorySupport resellArticleLikeRepositorySupport;


    @Override
    public List<ResellBoardResponse> getAllArticle() {

        List<ResellBoardResponse> articleList = new LinkedList<ResellBoardResponse>();
        List<ResellBoard> articles = resellBoardRepository.findAll();
        for (ResellBoard article : articles) {
            ResellBoardResponse resellBoardResponse = new ResellBoardResponse();
            setResellBoardResponse(article, resellBoardResponse);
            articleList.add(resellBoardResponse);
        }
        return articleList;
    }

    private void setResellBoardResponse(ResellBoard article, ResellBoardResponse resellBoardResponse) {
        resellBoardResponse.setId(article.getId());
        resellBoardResponse.setMemberId(article.getMemberId());
        resellBoardResponse.setTitle(article.getTitle());
        resellBoardResponse.setContent(article.getContent());
        resellBoardResponse.setPhoto(article.getPhoto());
        resellBoardResponse.setTag(article.getTag());
        resellBoardResponse.setRegtime(article.getRegtime());
        resellBoardResponse.setPrice(article.getPrice());
        resellBoardResponse.setLikeCnt(getLikeCountById(article.getId()));
    }

    @Override
    public void createArticle(ResellBoardPostRequest request, String photoPath) {
        ResellBoard article = request.toEntity();
        article.setPhoto(photoPath);
        article.setRegtime(LocalDateTime.now());
        article.setRegtime(LocalDateTime.now());
        resellBoardRepository.save(article);
    }

    @Override
    public long getLikeCountById(Integer articleId) {
        long likeCnt = resellArticleLikeRepositorySupport.countByArticleId(articleId);
        return likeCnt;
    }
}

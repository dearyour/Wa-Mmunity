package com.web.wam.model.service;

import com.web.wam.model.dto.resellboard.ResellBoardCmtResponse;
import com.web.wam.model.dto.resellboard.ResellBoardPostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardPutRequest;
import com.web.wam.model.dto.resellboard.ResellBoardResponse;
import com.web.wam.model.entity.resellboard.ResellArticleComment;
import com.web.wam.model.entity.resellboard.ResellBoard;
import com.web.wam.model.repository.resellboard.ResellArticleCommentRepositorySupport;
import com.web.wam.model.repository.resellboard.ResellArticleLikeRepository;
import com.web.wam.model.repository.resellboard.ResellArticleLikeRepositorySupport;
import com.web.wam.model.repository.resellboard.ResellBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service("resellBoardService")
public class ResellBoardServiceImpl implements ResellBoardService {

    @Autowired
    private ResellBoardRepository resellBoardRepository;

    @Autowired
    private ResellArticleLikeRepository resellArticleLikeRepository;

    @Autowired
    private ResellArticleLikeRepositorySupport resellArticleLikeRepositorySupport;

    @Autowired
    private ResellArticleCommentRepositorySupport resellArticleCommentRepositorySupport;


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
    public void updateArticle(ResellBoardPutRequest request, String newPhoto) {
        Optional<ResellBoard> article = resellBoardRepository.findById(request.getId());
        article.ifPresent(selectedArticle -> {
            selectedArticle = request.toEntity();
            selectedArticle.setRegtime(LocalDateTime.now());
            if(!newPhoto.equals("")) {
                selectedArticle.setPhoto(newPhoto);
            }
            resellBoardRepository.save(selectedArticle);
        });
    }

    @Override
    public long getLikeCountById(Integer articleId) {
        long likeCnt = resellArticleLikeRepositorySupport.countByArticleId(articleId);
        return likeCnt;
    }

    @Override
    public void deleteArticle(Integer articleId) {
        Optional<ResellBoard> article = resellBoardRepository.findById(articleId);
        article.ifPresent(selectedArticle -> {
            resellBoardRepository.delete(selectedArticle);
        });
    }

    @Override
    public ResellBoardResponse getArticleById(Integer articleId) {
        ResellBoardResponse resellBoardResponse = new ResellBoardResponse();
        Optional<ResellBoard> article = resellBoardRepository.findById(articleId);
        article.ifPresent(selectedArticle -> {
            setResellBoardResponse(selectedArticle, resellBoardResponse);
        });
        return resellBoardResponse;
    }

    @Override
    public List<ResellBoardCmtResponse> getCommentsById(Integer articleId) {
        List<ResellBoardCmtResponse> commentList = new LinkedList<ResellBoardCmtResponse>();
        List<ResellArticleComment> comments = resellArticleCommentRepositorySupport.findByArticleId(articleId);
        for (ResellArticleComment comment : comments) {
            ResellBoardCmtResponse resellBoardCmtResponse = new ResellBoardCmtResponse();
            setResellBoardCmtResponse(comment, resellBoardCmtResponse);
            commentList.add(resellBoardCmtResponse);
        }
        return commentList;
    }

    private void setResellBoardCmtResponse(ResellArticleComment comment, ResellBoardCmtResponse response) {
        response.setMemberId(comment.getMemberId());
        response.setContent(comment.getContent());
        response.setRegtime(comment.getRegtime());
        response.setId(comment.getId());
        String memberNickname = resellArticleCommentRepositorySupport.findMemberNicknameByMemberId(comment.getMemberId());
        response.setNickname(memberNickname);
        response.setArticleId(comment.getArticleId());
    }
}

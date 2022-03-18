package com.web.wam.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.wam.model.dto.freeboard.FreeboardPostRequest;
import com.web.wam.model.dto.freeboard.FreeboardPutRequest;
import com.web.wam.model.entity.freeboard.FreeArticleComment;
import com.web.wam.model.entity.freeboard.FreeBoard;
import com.web.wam.model.repository.freeboard.FreeArticleCommentRepository;
import com.web.wam.model.repository.freeboard.FreeArticleCommentRepositorySupport;
import com.web.wam.model.repository.freeboard.FreeBoardRepository;
import com.web.wam.model.repository.freeboard.FreeBoardRepositorySupport;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Autowired
	FreeBoardRepository freeBoardRepository;
	@Autowired
	FreeArticleCommentRepositorySupport freeArticleCommentRepositorySupport;
	

	@Override
	public List<FreeBoard> getAllArticle() {
		List<FreeBoard> articles = freeBoardRepository.findAll();
		return articles;
	}


	@Override
	public void createArticle(FreeboardPostRequest articleCreateInfo) {
		FreeBoard article= new FreeBoard();
		article.setMemberId(articleCreateInfo.getMemberId());
		article.setTitle(articleCreateInfo.getTitle());
		article.setContent(articleCreateInfo.getContent());
		article.setPhoto(articleCreateInfo.getPhoto());
		article.setTag(articleCreateInfo.getTag());
		article.setRegtime(LocalDateTime.now());
		freeBoardRepository.save(article);
	}


	@Override
	public void updateArticle(FreeboardPutRequest articleUpdateInfo) {
		Optional<FreeBoard> article = freeBoardRepository.findById(articleUpdateInfo.getArticleId());
		article.ifPresent(selectArticle->{
			selectArticle.setTitle(articleUpdateInfo.getTitle());
			selectArticle.setContent(articleUpdateInfo.getContent());
			selectArticle.setPhoto(articleUpdateInfo.getPhoto());
			selectArticle.setTag(articleUpdateInfo.getPhoto());
			selectArticle.setRegtime(LocalDateTime.now());
			freeBoardRepository.save(selectArticle);
		});
	}


	@Override
	public void deleteArticle(int articleId) {
		Optional<FreeBoard> article = freeBoardRepository.findById(articleId);
		article.ifPresent(selectArticle->{
			freeBoardRepository.delete(selectArticle);
		});
	}


	@Override
	public Optional<FreeBoard> getArticleById(int articleId) {
		Optional<FreeBoard> article = freeBoardRepository.findById(articleId);
		return article;
	}


	@Override
	public List<FreeArticleComment> getCommentsById(int articleId) {
		List<FreeArticleComment> comments = freeArticleCommentRepositorySupport.findByArticleId(articleId);
		return null;
	}

}

package com.web.wam.model.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.wam.model.dto.freeboard.FreeBoardCmtPostRequest;
import com.web.wam.model.dto.freeboard.FreeBoardCmtPutRequest;
import com.web.wam.model.dto.freeboard.FreeBoardLikePostRequest;
import com.web.wam.model.dto.freeboard.FreeBoardPostRequest;
import com.web.wam.model.dto.freeboard.FreeBoardPutRequest;
import com.web.wam.model.dto.freeboard.FreeBoardResponse;
import com.web.wam.model.dto.freeboard.FreeaBoardCmtResponse;
import com.web.wam.model.entity.freeboard.FreeArticleComment;
import com.web.wam.model.entity.freeboard.FreeArticleLike;
import com.web.wam.model.entity.freeboard.FreeBoard;
import com.web.wam.model.repository.freeboard.FreeArticleCommentRepository;
import com.web.wam.model.repository.freeboard.FreeArticleCommentRepositorySupport;
import com.web.wam.model.repository.freeboard.FreeArticleLikeRepository;
import com.web.wam.model.repository.freeboard.FreeArticleLikeRepositorySupport;
import com.web.wam.model.repository.freeboard.FreeBoardRepository;
import com.web.wam.model.repository.freeboard.FreeBoardRepositorySupport;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	FreeBoardRepository freeBoardRepository;
	@Autowired
	FreeBoardRepositorySupport freeBoardRepositorySupport;
	@Autowired
	FreeArticleCommentRepository freeArticleCommentRepository;
	@Autowired
	FreeArticleCommentRepositorySupport freeArticleCommentRepositorySupport;
	@Autowired
	FreeArticleLikeRepository freeArticleLikeRepository;
	@Autowired
	FreeArticleLikeRepositorySupport freeArticleLikeRepositorySupport;

	@Override
	public List<FreeBoardResponse> getAllArticle() {

		List<FreeBoardResponse> articleList = new LinkedList<FreeBoardResponse>();
		List<FreeBoard> articles = freeBoardRepository.findAll();
		for (FreeBoard article : articles) {
			FreeBoardResponse freeBoardResponse = new FreeBoardResponse();
			setFreeBoardResponse(article, freeBoardResponse);
			articleList.add(freeBoardResponse);
		}
		return articleList;
	}

	private void setFreeBoardResponse(FreeBoard article, FreeBoardResponse freeBoardResponse) {
		freeBoardResponse.setArticleId(article.getArticleId());
		freeBoardResponse.setMemberId(article.getMemberId());
		freeBoardResponse.setTitle(article.getTitle());
		freeBoardResponse.setContent(article.getContent());
		freeBoardResponse.setPhoto(article.getPhoto());
		freeBoardResponse.setTag(article.getTag());
		freeBoardResponse.setRegtime(article.getRegtime());
		freeBoardResponse.setLikeCnt(getLikeCountById(article.getArticleId()));
	}

	@Override
	public void createArticle(FreeBoardPostRequest articleCreateInfo) {
		FreeBoard article = new FreeBoard();
		article.setMemberId(articleCreateInfo.getMemberId());
		article.setTitle(articleCreateInfo.getTitle());
		article.setContent(articleCreateInfo.getContent());
		article.setPhoto(articleCreateInfo.getPhoto());
		article.setTag(articleCreateInfo.getTag());
		article.setRegtime(LocalDateTime.now());
		freeBoardRepository.save(article);
	}

	@Override
	public void updateArticle(FreeBoardPutRequest articleUpdateInfo) {
		Optional<FreeBoard> article = freeBoardRepository.findById(articleUpdateInfo.getArticleId());
		article.ifPresent(selectArticle -> {
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
		article.ifPresent(selectArticle -> {
			freeBoardRepository.delete(selectArticle);
		});
	}

	@Override
	public FreeBoardResponse getArticleById(int articleId) {
		FreeBoardResponse freeBoardResponse = new FreeBoardResponse();
		Optional<FreeBoard> article = freeBoardRepository.findById(articleId);
		article.ifPresent(selectArticle -> {
			setFreeBoardResponse(selectArticle, freeBoardResponse);
		});
		return freeBoardResponse;
	}

	@Override
	public List<FreeaBoardCmtResponse> getCommentsById(int articleId) {
		List<FreeaBoardCmtResponse> commentList = new LinkedList<FreeaBoardCmtResponse>();
		List<FreeArticleComment> comments = freeArticleCommentRepositorySupport.findByArticleId(articleId);
		for (FreeArticleComment comment : comments) {
			FreeaBoardCmtResponse freeaBoardCmtResponse = new FreeaBoardCmtResponse();
			setFreeBoardCmtResponse(comment, freeaBoardCmtResponse);
			commentList.add(freeaBoardCmtResponse);
		}
		return commentList;
	}

	private void setFreeBoardCmtResponse(FreeArticleComment comment, FreeaBoardCmtResponse freeaBoardCmtResponse) {
		freeaBoardCmtResponse.setMemberId(comment.getMemberId());
		freeaBoardCmtResponse.setContent(comment.getContent());
		freeaBoardCmtResponse.setRegtime(comment.getRegtime());
	}

	@Override
	public long getLikeCountById(int articleId) {
		long likeCnt = freeArticleLikeRepositorySupport.countByArticleId(articleId);
		return likeCnt;
	}

	@Override
	public List<FreeBoardResponse> getArticleByMemberId(int memberId) {
		List<FreeBoardResponse> articles = freeBoardRepositorySupport.findByMemberId(memberId);
		return articles;
	}

	@Override
	public List<FreeaBoardCmtResponse> getCommentByMemberId(int memberId) {
		List<FreeaBoardCmtResponse> commentList = new LinkedList<FreeaBoardCmtResponse>();
		List<FreeArticleComment> comments = freeArticleCommentRepositorySupport.findByMemberId(memberId);
		for (FreeArticleComment comment : comments) {
			FreeaBoardCmtResponse freeaBoardCmtResponse = new FreeaBoardCmtResponse();
			setFreeBoardCmtResponse(comment, freeaBoardCmtResponse);
			commentList.add(freeaBoardCmtResponse);
		}
		return commentList;
	}

	@Override
	public void createComment(FreeBoardCmtPostRequest commentCreateInfo) {
		FreeArticleComment comment = new FreeArticleComment();
		comment.setArticleId(commentCreateInfo.getAtricleId());
		comment.setMemberId(commentCreateInfo.getMemberId());
		comment.setContent(commentCreateInfo.getContent());
		comment.setRegtime(LocalDateTime.now());
		freeArticleCommentRepository.save(comment);
	}

	@Override
	public void updateComment(FreeBoardCmtPutRequest commentUpdateInfo) {
		Optional<FreeArticleComment> comment = freeArticleCommentRepository.findById(commentUpdateInfo.getCommentId());
		comment.ifPresent(selectComment -> {
			selectComment.setContent(commentUpdateInfo.getContent());
			selectComment.setRegtime(LocalDateTime.now());
			freeArticleCommentRepository.save(selectComment);
		});
	}

	@Override
	public void deleteComment(int commentId) {
		Optional<FreeArticleComment> comment = freeArticleCommentRepository.findById(commentId);
		comment.ifPresent(selectComment -> {
			freeArticleCommentRepository.delete(selectComment);
		});
	}

	@Override
	public void addLike(FreeBoardLikePostRequest likeAddInfo) {
		FreeArticleLike like = new FreeArticleLike();
		like.setArticleId(likeAddInfo.getAtricleId());
		like.setMemberId(likeAddInfo.getMemberId());
		freeArticleLikeRepository.save(like);
	}

	@Override
	public void cancelLike(FreeBoardLikePostRequest likeCancelInfo) {
		freeArticleLikeRepositorySupport.cancelLike(likeCancelInfo);
	}

	@Override
	public List<FreeBoardResponse> getArticleByKeyword(String keyword) {
		List<FreeBoardResponse> articles = freeBoardRepositorySupport.getArticleByKeyword(keyword);
		return articles;
	}

	@Override
	public List<Integer> getLikeArticleByMemberId(int memberId) {
		List<Integer> articles = freeArticleLikeRepositorySupport.getLikeArticleByMemberId(memberId);
		return articles;
	}

}

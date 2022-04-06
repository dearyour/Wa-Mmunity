package com.web.wam.model.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.wam.model.dto.resellboard.ResellBoardCmtPostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardCmtPutRequest;
import com.web.wam.model.dto.resellboard.ResellBoardCmtResponse;
import com.web.wam.model.dto.resellboard.ResellBoardLikePostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardPostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardPutRequest;
import com.web.wam.model.dto.resellboard.ResellBoardResponse;
import com.web.wam.model.entity.resellboard.ResellArticleComment;
import com.web.wam.model.entity.resellboard.ResellArticleLike;
import com.web.wam.model.entity.resellboard.ResellBoard;
import com.web.wam.model.repository.resellboard.ResellArticleCommentRepository;
import com.web.wam.model.repository.resellboard.ResellArticleCommentRepositorySupport;
import com.web.wam.model.repository.resellboard.ResellArticleLikeRepository;
import com.web.wam.model.repository.resellboard.ResellArticleLikeRepositorySupport;
import com.web.wam.model.repository.resellboard.ResellBoardRepository;
import com.web.wam.model.repository.resellboard.ResellBoardRepositorySupport;

@Service("resellBoardService")
public class ResellBoardServiceImpl implements ResellBoardService {

	@Autowired
	private ResellBoardRepository resellBoardRepository;

	@Autowired
	private ResellBoardRepositorySupport resellBoardRepositorySupport;

	@Autowired
	private ResellArticleLikeRepository resellArticleLikeRepository;

	@Autowired
	private ResellArticleLikeRepositorySupport resellArticleLikeRepositorySupport;

	@Autowired
	private ResellArticleCommentRepository resellArticleCommentRepository;

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
			if (!newPhoto.equals("")) {
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
	public List<ResellBoardResponse> getArticlesByMemberId(Integer memberId) {
		return resellBoardRepositorySupport.findByMemberId(memberId);
	}

	@Override
	public List<ResellBoardCmtResponse> getCommentsByMemberId(Integer memberId) {
		List<ResellBoardCmtResponse> commentList = new LinkedList<ResellBoardCmtResponse>();
		List<ResellArticleComment> comments = resellArticleCommentRepositorySupport.findByMemberId(memberId);
		for (ResellArticleComment comment : comments) {
			ResellBoardCmtResponse resellBoardCmtResponse = new ResellBoardCmtResponse();
			setResellBoardCmtResponse(comment, resellBoardCmtResponse);
			commentList.add(resellBoardCmtResponse);
		}
		return commentList;
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
		// Optional<Member> member = memberRepository.findById(comment.getMemberId());

//        member.ifPresent(selectedMember -> {
//            response.setNickname(selectedMember.getNickname());
//        });
		response.setNickname(resellArticleCommentRepositorySupport.findMemberNicknameByMemberId(comment.getMemberId()));
		response.setArticleId(comment.getArticleId());
	}

	@Override
	public void createComment(ResellBoardCmtPostRequest request) {
		ResellArticleComment comment = new ResellArticleComment();
		comment.setArticleId(request.getArticleId());
		comment.setContent(request.getContent());
		comment.setMemberId(request.getMemberId());
		comment.setRegtime(LocalDateTime.now());
		resellArticleCommentRepository.save(comment);
	}

	@Override
	public void updateComment(ResellBoardCmtPutRequest request) {
		Optional<ResellArticleComment> comment = resellArticleCommentRepository.findById(request.getId());
		comment.ifPresent(selectedCmt -> {
			selectedCmt.setContent(request.getContent());
			selectedCmt.setRegtime(LocalDateTime.now());
			resellArticleCommentRepository.save(selectedCmt);
		});
	}

	@Override
	public void deleteComment(Integer commentId) {
		Optional<ResellArticleComment> comment = resellArticleCommentRepository.findById(commentId);
		comment.ifPresent(selectedCmt -> {
			resellArticleCommentRepository.delete(selectedCmt);
		});
	}

	@Override
	public void addLike(ResellBoardLikePostRequest request) {
		ResellArticleLike like = new ResellArticleLike();
		like.setArticleId(request.getArticleId());
		like.setMemberId(request.getMemberId());
		resellArticleLikeRepository.save(like);
	}

	@Override
	public void deleteLike(ResellBoardLikePostRequest request) {
		resellArticleLikeRepositorySupport.cancelLike(request);
	}

	@Override
	public List<ResellBoardResponse> getArticleByKeyword(String keyword) {
		List<ResellBoardResponse> articles = resellBoardRepositorySupport.getArticleByKeyword(keyword);
		return articles;
	}

	@Override
	public List<Integer> getLikeArticleByMemberId(int memberId) {
		List<Integer> articles = resellArticleLikeRepositorySupport.getLikeArticleByMemberId(memberId);
		return articles;
	}

}

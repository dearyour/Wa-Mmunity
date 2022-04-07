package com.web.wam.controller;

import java.util.List;

import com.web.wam.model.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.dto.freeboard.FreeBoardArticleGetResponse;
import com.web.wam.model.dto.freeboard.FreeBoardCmtGetResponse;
import com.web.wam.model.dto.freeboard.FreeBoardCmtPostRequest;
import com.web.wam.model.dto.freeboard.FreeBoardCmtPutRequest;
import com.web.wam.model.dto.freeboard.FreeBoardGetResponse;
import com.web.wam.model.dto.freeboard.FreeBoardLikePostRequest;
import com.web.wam.model.dto.freeboard.FreeBoardPostRequest;
import com.web.wam.model.dto.freeboard.FreeBoardPutRequest;
import com.web.wam.model.dto.freeboard.FreeBoardResponse;
import com.web.wam.model.dto.freeboard.FreeaBoardCmtResponse;
import com.web.wam.model.service.FreeBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE, RequestMethod.OPTIONS }, maxAge = 6000)
@Api(value = "자유게시판 API", tags = { "freeboard" })
@RestController
@RequestMapping("/api/freeboard")
public class FreeBoardController {
	@Autowired
	FreeBoardService freeBoardService;

	@Autowired
	S3Service s3Service;

	@GetMapping
	@ApiOperation(value = "게시글 리스트", notes = "자유게시판 전체 글 불러오기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getAllArticle() {
		List<FreeBoardArticleGetResponse> articles = freeBoardService.getAllArticle();
		return ResponseEntity.status(200).body(FreeBoardGetResponse.of(200, articles));
	}

	@PostMapping
	@ApiOperation(value = "게시글 작성", notes = "자유게시판 게시글 작성")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> createArticle(
			@RequestPart @ApiParam(value = "사진 파일", required = false) MultipartFile photo,
			@RequestPart @ApiParam(value = "게시글 제목", required = true) String title,
			@RequestPart @ApiParam(value = "게시글 내용") String content,
			@RequestPart @ApiParam(value = "게시글 태그") String tag,
			@RequestPart @ApiParam(value = "작성자 id", required = true) Integer member_id
	) {
		FreeBoardPostRequest articleCreateInfo = new FreeBoardPostRequest();
		articleCreateInfo.setMemberId(member_id);
		articleCreateInfo.setTitle(title);
		articleCreateInfo.setContent(content);
		articleCreateInfo.setTag(tag);
		String photoPath = "";
		if (photo != null) {
			photoPath = s3Service.uploadToFreeboard(photo);
			articleCreateInfo.setPhoto(photoPath);
		}
		freeBoardService.createArticle(articleCreateInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}

	@PutMapping
	@ApiOperation(value = "게시글 수정", notes = "자유게시판 게시글 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> updateArticle(
			@RequestPart @ApiParam(value = "사진 파일", required = false) MultipartFile photo,
			@RequestPart @ApiParam(value = "게시글 제목", required = true) String title,
			@RequestPart @ApiParam(value = "게시글 내용") String content,
			@RequestPart @ApiParam(value = "게시글 태그") String tag,
			@RequestPart @ApiParam(value = "수정할 글의 id", required = true) Integer article_id
	) {
		FreeBoardPutRequest articleUpdateInfo = new FreeBoardPutRequest();
		articleUpdateInfo.setArticleId(article_id);
		articleUpdateInfo.setTitle(title);
		articleUpdateInfo.setContent(content);
		articleUpdateInfo.setTag(tag);
		String photoPath = "";
		if (!photo.isEmpty()) {
			photoPath = s3Service.uploadToFreeboard(photo);
			articleUpdateInfo.setPhoto(photoPath);
		}
		freeBoardService.updateArticle(articleUpdateInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}

	@DeleteMapping("/{articleId}")
	@ApiOperation(value = "게시글 삭제", notes = "자유게시판 게시글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> deleteArticle(
			@ApiParam(value = "게시글 삭제 정보", required = true) @PathVariable("articleId") int articleId) {
		freeBoardService.deleteArticle(articleId);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}

	@GetMapping("/article/{articleId}")
	@ApiOperation(value = "선택한 게시글 보기", notes = "자유게시판 게시글 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getArticleById(
			@ApiParam(value = "선택한 게시글 정보", required = true) @PathVariable("articleId") int articleId) {
		FreeBoardResponse article = freeBoardService.getArticleById(articleId);
		List<FreeaBoardCmtResponse> comments = freeBoardService.getCommentsById(articleId);

		return ResponseEntity.status(200)
				.body(BaseResponse.of(200, new FreeBoardArticleGetResponse(article, comments)));
	}

	@GetMapping("/myarticle/{memberId}")
	@ApiOperation(value = "내가 작성한 글 리스트", notes = "자유게시판 내가 작성한 글 목록 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getArticleByMemberId(
			@ApiParam(value = "멤버 아이디 정보", required = true) @PathVariable("memberId") int memberId) {
		List<FreeBoardResponse> articles = freeBoardService.getArticleByMemberId(memberId);
		return ResponseEntity.status(200).body(FreeBoardGetResponse.of(200, articles));
	}

	@GetMapping("/mycomment/{memberId}")
	@ApiOperation(value = "내가 작성한 댓글 리스트", notes = "자유게시판 내가 작성한 댓글 목록 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getCommentByMemberId(
			@ApiParam(value = "멤버 아이디 정보", required = true) @PathVariable("memberId") int memberId) {
		List<FreeaBoardCmtResponse> comments = freeBoardService.getCommentByMemberId(memberId);
		return ResponseEntity.status(200).body(FreeBoardCmtGetResponse.of(200, comments));
	}

	@PostMapping("/comment")
	@ApiOperation(value = "댓글 작성", notes = "자유게시판 댓글 작성")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> createComment(
			@RequestBody @ApiParam(value = "댓글 생성 정보", required = true) FreeBoardCmtPostRequest commentCreateInfo) {
		freeBoardService.createComment(commentCreateInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}

	@PutMapping("/comment")
	@ApiOperation(value = "댓글 수정", notes = "자유게시판 댓글 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> updateComment(
			@RequestBody @ApiParam(value = "댓글 수정 정보", required = true) FreeBoardCmtPutRequest commentUpdateInfo) {
		freeBoardService.updateComment(commentUpdateInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}

	@DeleteMapping("/comment/{commentId}")
	@ApiOperation(value = "댓글 삭제", notes = "자유게시판 댓글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> deleteComment(
			@ApiParam(value = "댓글 삭제 정보", required = true) @PathVariable("commentId") int commentId) {
		freeBoardService.deleteComment(commentId);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}

	@PostMapping("/like")
	@ApiOperation(value = "추천 누르기", notes = "자유게시판 게시글 추천")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> addLike(
			@RequestBody @ApiParam(value = "추천 생성 정보", required = true) FreeBoardLikePostRequest likeAddInfo) {
		freeBoardService.addLike(likeAddInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}

	@DeleteMapping("/like")
	@ApiOperation(value = "추천 취소하기", notes = "자유게시판 게시글 추천 취소하기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> cancelLike(
			@RequestBody @ApiParam(value = "추천 삭제 정보", required = true) FreeBoardLikePostRequest likeCancelInfo) {
		freeBoardService.cancelLike(likeCancelInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}

	@GetMapping("/like/{memberId}")
	@ApiOperation(value = "내가 추천 누른 글 리스트", notes = "자유게시판 내가 추천 누른 글 리스트")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getLikeArticleByMemberId(
			@ApiParam(value = "멤버아이디", required = true) @PathVariable("memberId") int memberId) {
		List<Integer> articles = freeBoardService.getLikeArticleByMemberId(memberId);
		return ResponseEntity.status(200).body(FreeBoardGetResponse.of(200, articles));

	}

	@GetMapping("/search/{keyword}")
	@ApiOperation(value = "게시글 검색", notes = "자유게시판 게시글 검색(제목으로만)")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getArticleByKeyword(
			@ApiParam(value = "검색 내용 정보", required = true) @PathVariable("keyword") String keyword) {
		List<FreeBoardResponse> articles = freeBoardService.getArticleByKeyword(keyword);
		return ResponseEntity.status(200).body(FreeBoardGetResponse.of(200, articles));

	}
}

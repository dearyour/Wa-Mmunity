package com.web.wam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.dto.freeboard.FreeBoardGetResponse;
import com.web.wam.model.dto.resellboard.ResellBoardCmtPostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardCmtPutRequest;
import com.web.wam.model.dto.resellboard.ResellBoardLikePostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardPostRequest;
import com.web.wam.model.dto.resellboard.ResellBoardPutRequest;
import com.web.wam.model.service.ResellBoardService;
import com.web.wam.model.service.S3Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "리셀게시판 API", tags = { "resellboard" })
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE, RequestMethod.OPTIONS }, maxAge = 6000)
@RequestMapping("/api/resellboard")
public class ResellBoardController {

	@Autowired
	ResellBoardService resellBoardService;

	@Autowired
	S3Service s3Service;

	private final String MESSAGE_200 = "성공";
	private final String MESSAGE_401 = "인증 실패";
	private final String MESSAGE_500 = "서버 오류";
	private final String SUCCESS = "success";
	private final String MSG = "message";

	@PostMapping()
	@ApiOperation(value = "게시글 작성", notes = "리셀 게시판 게시글 작성 API")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> post(
			@RequestPart @ApiParam(value = "와인 사진 파일.", required = false) MultipartFile photo,
			@RequestPart @ApiParam(value = "게시글 제목", required = true) String title,
			@RequestPart @ApiParam(value = "게시글 내용") String content,
			@RequestPart @ApiParam(value = "게시글 태그") String tag,
			@RequestPart @ApiParam(value = "와인 가격", required = true) String price,
			@RequestPart @ApiParam(value = "멤버 id", required = true) String member_id ) {

		ResellBoardPostRequest request = new ResellBoardPostRequest();
		request.setContent(content);
		request.setMemberId(Integer.parseInt(member_id));
		request.setTitle(title);
		request.setTag(tag);
		request.setPrice(Integer.parseInt(price));

		String photoPath = "";
		if (photo != null) {
			photoPath = s3Service.uploadToResellboard(photo);
		}
		resellBoardService.createArticle(request, photoPath);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@GetMapping()
	@ApiOperation(value = "게시글 리스트", notes = "리셀 게시판 게시물 목록 반환 API")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> getAllArticle() {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("articleList", resellBoardService.getAllArticle());
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@PutMapping()
	@ApiOperation(value = "게시글 수정", notes = "리셀 게시판 게시물 수정 API")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> updateArticle(
			@RequestPart @ApiParam(value = "수정할 사진 파일.", required = false) MultipartFile photo,
			@RequestPart @ApiParam(value = "게시글 제목", required = true) String title,
			@RequestPart @ApiParam(value = "게시글 내용") String content,
			@RequestPart @ApiParam(value = "게시글 태그") String tag,
			@RequestPart @ApiParam(value = "와인 가격", required = true) String price,
			@RequestPart @ApiParam(value = "멤버 id", required = true) String member_id,
			@RequestPart @ApiParam(value = "수정할 리셀 글 id", required = true) String article_id) {
		ResellBoardPutRequest request = new ResellBoardPutRequest();
		request.setContent(content);
		request.setMemberId(Integer.parseInt(member_id));
		request.setTitle(title);
		request.setTag(tag);
		request.setPrice(Integer.parseInt(price));
		request.setId(Integer.parseInt(article_id));

		String photoPath = "";
		if (!photo.isEmpty()) {
			photoPath = s3Service.uploadToResellboard(photo);
		}
		resellBoardService.updateArticle(request, photoPath);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@DeleteMapping("/{article_id}")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> deleteArticle(
			@ApiParam(value = "게시글 삭제 정보", required = true) @PathVariable("article_id") Integer articleId) {
		resellBoardService.deleteArticle(articleId);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@GetMapping("/{article_id}")
	@ApiOperation(value = "선택한 게시글 정보", notes = "리셀 게시판 선택한 게시물 내용 반환 API")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> getArticleById(
			@ApiParam(value = "선택한 게시글 정보", required = true) @PathVariable("article_id") Integer articleId) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("article", resellBoardService.getArticleById(articleId));
		resultMap.put("comments", resellBoardService.getCommentsById(articleId));
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@GetMapping("comment/{article_id}")
	@ApiOperation(value = "선택한 게시글의 댓글 정보", notes = "리셀 게시판 선택한 게시물의 댓글 목록 반환 API")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> getCommentsById(
			@ApiParam(value = "선택한 게시글 정보", required = true) @PathVariable("article_id") Integer articleId) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("comments", resellBoardService.getCommentsById(articleId));
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@PostMapping("/comment")
	@ApiOperation(value = "댓글 작성", notes = "리셀게시판 댓글 작성")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> createComment(
			@RequestBody @ApiParam(value = "댓글 생성 정보", required = true) ResellBoardCmtPostRequest request) {
		resellBoardService.createComment(request);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@PutMapping("/comment")
	@ApiOperation(value = "댓글 수정", notes = "리셀게시판 댓글 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> updateComment(
			@RequestBody @ApiParam(value = "댓글 수정 정보", required = true) ResellBoardCmtPutRequest request) {
		resellBoardService.updateComment(request);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@DeleteMapping("/comment/{commentId}")
	@ApiOperation(value = "댓글 삭제", notes = "리셀게시판 댓글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> deleteComment(
			@ApiParam(value = "댓글 삭제 정보", required = true) @PathVariable("commentId") Integer commentId) {
		resellBoardService.deleteComment(commentId);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@GetMapping("/myarticle/{member_id}")
	@ApiOperation(value = "내가 작성한 글 리스트", notes = "리셀게시판 내가 작성한 글 목록 반환 API")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> getArticleByMemberId(
			@ApiParam(value = "멤버 아이디 정보", required = true) @PathVariable("member_id") Integer memberId) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("articleList", resellBoardService.getArticlesByMemberId(memberId));
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@GetMapping("/mycomment/{member_id}")
	@ApiOperation(value = "내가 작성한 댓글 리스트", notes = "리셀게시판 내가 작성한 댓글 목록 반환 API")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> getCommentsByMemberId(
			@ApiParam(value = "멤버 아이디 정보", required = true) @PathVariable("member_id") Integer memberId) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("comments", resellBoardService.getCommentsByMemberId(memberId));
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@PostMapping("/like")
	@ApiOperation(value = "추천 누르기", notes = "리셀게시판 게시글 추천")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> addLike(
			@RequestBody @ApiParam(value = "추천 생성 정보", required = true) ResellBoardLikePostRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		resellBoardService.addLike(request);
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@DeleteMapping("/like")
	@ApiOperation(value = "추천 취소하기", notes = "리셀게시판 게시글 추천 취소하기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> cancelLike(
			@RequestBody @ApiParam(value = "추천 삭제 정보", required = true) ResellBoardLikePostRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		resellBoardService.deleteLike(request);
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@GetMapping("/like/{memberId}")
	@ApiOperation(value = "내가 추천 누른 글 리스트", notes = "리뷰게시판 내가 추천 누른 글 리스트")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getLikeArticleByMemberId(
			@ApiParam(value = "멤버아이디", required = true) @PathVariable("memberId") int memberId) {
		List<Integer> articles = resellBoardService.getLikeArticleByMemberId(memberId);
		return ResponseEntity.status(200).body(FreeBoardGetResponse.of(200, articles));

	}

	@GetMapping("/search/{keyword}")
	@ApiOperation(value = "게시글 검색", notes = "리셀게시판 게시글 검색(제목으로만)")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> getArticleByKeyword(
			@ApiParam(value = "검색 키워드", required = true) @PathVariable("keyword") String keyword) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("articleList", resellBoardService.getArticleByKeyword(keyword));
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

}

package com.web.wam.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.dto.freeboard.FreeBoardArticleGetResponse;
import com.web.wam.model.dto.freeboard.FreeboardCmtGetResponse;
import com.web.wam.model.dto.freeboard.FreeboardCmtPostRequest;
import com.web.wam.model.dto.freeboard.FreeboardCmtPutRequest;
import com.web.wam.model.dto.freeboard.FreeboardGetResponse;
import com.web.wam.model.dto.freeboard.FreeboardLikePostRequest;
import com.web.wam.model.dto.freeboard.FreeboardPostRequest;
import com.web.wam.model.dto.freeboard.FreeboardPutRequest;
import com.web.wam.model.entity.freeboard.FreeArticleComment;
import com.web.wam.model.entity.freeboard.FreeBoard;
import com.web.wam.model.service.FreeBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "자유게시판 API", tags = { "freeboard" })
@RestController
@RequestMapping("/freeboard")
public class FreeBoardController {
	@Autowired
	FreeBoardService freeBoardService;
	
	@GetMapping
	@ApiOperation(value = "게시글 리스트", notes = "자유게시판 전체 글 불러오기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getAllArticle(){
		Map<FreeBoard,Long> articles= freeBoardService.getAllArticle();
		return ResponseEntity.status(200).body(FreeboardGetResponse.of(200, "Success", articles));
	}
	
	@PostMapping
	@ApiOperation(value = "게시글 작성", notes = "자유게시판 게시글 작성")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> createArticle(@RequestBody @ApiParam(value = "게시글 생성 정보", required = true) FreeboardPostRequest articleCreateInfo){
		freeBoardService.createArticle(articleCreateInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}
	
	@PutMapping
	@ApiOperation(value = "게시글 수정", notes = "자유게시판 게시글 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> updateArticle(@RequestBody @ApiParam(value = "게시글 수정 정보", required = true) FreeboardPutRequest articleUpdateInfo){
		freeBoardService.updateArticle(articleUpdateInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}
	
	@DeleteMapping("/{articleId}")
	@ApiOperation(value = "게시글 삭제", notes = "자유게시판 게시글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> deleteArticle( @ApiParam(value = "게시글 삭제 정보", required = true) @PathVariable("articleId") int articleId){
		freeBoardService.deleteArticle(articleId); 
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}
	
	@GetMapping("/article/{articleId}")
	@ApiOperation(value ="선택한 게시글 보기", notes = "자유게시판 게시글 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getArticleById( @ApiParam(value = "선택한 게시글 정보", required = true) @PathVariable("articleId") int articleId){
		Optional<FreeBoard> article = freeBoardService.getArticleById(articleId); 
		List<FreeArticleComment> comments = freeBoardService.getCommentsById(articleId);
		long likeCnt = freeBoardService.getLikeCountById(articleId);
		return ResponseEntity.status(200).body(FreeBoardArticleGetResponse.of(200, "Success", article,comments,likeCnt));
	}
	
	@GetMapping("/myarticle/{memberId}")
	@ApiOperation(value ="내가 작성한 글 리스트", notes = "자유게시판 내가 작성한 글 목록 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getArticleByMemberId( @ApiParam(value = "멤버 아이디 정보", required = true) @PathVariable("memberId") int memberId){
		Map<FreeBoard,Long> articles = freeBoardService.getArticleByMemberId(memberId); 
		return ResponseEntity.status(200).body(FreeboardGetResponse.of(200, "Success", articles));
	}
	
	@GetMapping("/mycomment/{memberId}")
	@ApiOperation(value ="내가 작성한 댓글 리스트", notes = "자유게시판 내가 작성한 댓글 목록 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getCommentByMemberId( @ApiParam(value = "멤버 아이디 정보", required = true) @PathVariable("memberId") int memberId){
		List<FreeArticleComment> comments = freeBoardService.getCommentByMemberId(memberId); 
		return ResponseEntity.status(200).body(FreeboardCmtGetResponse.of(200, "Success", comments));
	}
	
	@PostMapping("/comment")
	@ApiOperation(value = "댓글 작성", notes = "자유게시판 댓글 작성")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> createComment(@RequestBody @ApiParam(value = "댓글 생성 정보", required = true) FreeboardCmtPostRequest commentCreateInfo){
		freeBoardService.createComment(commentCreateInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}
	
	@PutMapping("/comment")
	@ApiOperation(value = "댓글 수정", notes = "자유게시판 댓글 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> updateComment(@RequestBody @ApiParam(value = "댓글 수정 정보", required = true) FreeboardCmtPutRequest commentUpdateInfo){
		freeBoardService.updateComment(commentUpdateInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}
	
	@DeleteMapping("/comment/{commentId}")
	@ApiOperation(value = "댓글 삭제", notes = "자유게시판 댓글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> deleteComment( @ApiParam(value = "댓글 삭제 정보", required = true) @PathVariable("commentId") int commentId){
		freeBoardService.deleteComment(commentId); 
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}
	
	@PostMapping("/like")
	@ApiOperation(value = "추천 누르기", notes = "자유게시판 게시글 추천")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> addLike(@RequestBody @ApiParam(value ="추천 생성 정보", required = true) FreeboardLikePostRequest likeAddInfo){
		freeBoardService.addLike(likeAddInfo);
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}
	
	@DeleteMapping("/like")
	@ApiOperation(value = "추천 취소하기", notes = "자유게시판 게시글 추천 취소하기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> cancelLike(@RequestBody @ApiParam(value = "추천 삭제 정보", required = true) FreeboardLikePostRequest likeCancelInfo){
		freeBoardService.cancelLike(likeCancelInfo); 
		return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
	}
	
	@GetMapping("/search/{keyword}")
	@ApiOperation(value ="게시글 검색", notes = "자유게시판 게시글 검색(제목으로만)")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponse.class),
			@ApiResponse(code = 404, message = "게시글 없음", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponse.class) })

	public ResponseEntity<? extends BaseResponse> getArticleByKeyword( @ApiParam(value = "검색 내용 정보", required = true) @PathVariable("keyword") String keyword){
		Map<FreeBoard,Long> articles = freeBoardService.getArticleByKeyword(keyword); 
		return ResponseEntity.status(200).body(FreeboardGetResponse.of(200, "Success", articles));
	}
}

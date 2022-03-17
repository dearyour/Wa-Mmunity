package com.web.wam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.wam.model.dto.BaseResponse;
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
	
//	@GetMapping
//	@ApiOperation(value = "게시글 리스트", notes = "자유게시판 전체 글 불러오기")
//	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
//			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
//			@ApiResponse(code = 404, message = "보드 없음", response = BaseResponseBody.class),
//			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class) })
//
//	public ResponseEntity<? extends BaseResponseBody> create(
//			@RequestBody @ApiParam(value = "보드 생성 정보", required = true) BoardCreatePostReq boardCreateInfo) {
//
//		freeBoardService.createBoard(boardCreateInfo);
//		return ResponseEntity.status(200).body(BoardCreatePostRes.of(200, "Success", boardRandom));
//	}
}

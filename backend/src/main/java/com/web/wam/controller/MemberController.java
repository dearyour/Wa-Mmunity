package com.web.wam.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.dto.member.ChangeNicknameRequest;
import com.web.wam.model.dto.member.ChangePasswordRequest;
import com.web.wam.model.dto.member.SigninRequest;
import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS} , maxAge = 6000)
@RequestMapping("/api/member")
@Api(value = "회원관리 API", tags = { "member" })
@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	private final String MESSAGE_200 = "성공";
	private final String MESSAGE_401 = "인증 실패";
	private final String MESSAGE_500 = "서버 오류";
	private final String SUCCESS = "success";
	private final String MSG = "message";

	@PostMapping("/signup")
	@ApiOperation(value = "가입하기", notes = "회원가입 요청 API")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> signup(@RequestBody @Valid SignupRequest request) {
		memberService.signup(request);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@PostMapping("/signin")
	@ApiOperation(value = "로그인요청", notes = "로그인 요청 API")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> signin(@RequestBody SigninRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("token", memberService.signin(request));
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@GetMapping("/idcheck/{email}")
	@ApiOperation(value = "아이디 중복검사", notes = "아이디 중복검사 API")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> idcheck(
			@PathVariable("email") @ApiParam(value = "유저의 email.", required = true) String email) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("isok", memberService.idcheck(email));
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@GetMapping("/ismember/{email}")
	@ApiOperation(value = "회원 확인", notes = "이메일 입력 후 회원인지 확인")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 400, message = "이메일을 다시 입력해주세요.", response = BaseResponse.class),
			@ApiResponse(code = 401, message = "이메일을 다시 입력해주세요.", response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> isMember(
			@PathVariable("email") @ApiParam(value = "유저의 email.", required = true) String email) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("newpass", memberService.createNewPassword(email));
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@PatchMapping("/password")
	@ApiOperation(value = "회원 확인", notes = "이메일 입력 후 회원인지 확인")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> isMember(@RequestBody @Valid ChangePasswordRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		memberService.changePassword(request);
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@GetMapping("/{email}")
	@ApiOperation(value = "회원 정보 보기", notes = "아이디와 일치하는 회원 정보 불러오기")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> findMember(
			@PathVariable("email") @ApiParam(value = "유저의 email.", required = true) String email) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("member", memberService.findMemberInfo(email));
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}

	@PatchMapping("/nickname")
	@ApiOperation(value = "닉네임 변경", notes = "닉네임 변경 API")
	@ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
			@ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
			@ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
	public ResponseEntity<? extends BaseResponse> updateMemberNickname(
			@RequestBody @Valid ChangeNicknameRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		memberService.updateMemberNickname(request);
		resultMap.put(MSG, SUCCESS);
		return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
	}
}

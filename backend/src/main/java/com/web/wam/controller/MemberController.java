package com.web.wam.controller;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.service.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RequestMapping("/member")
@Api(value = "회원관리 API", tags = { "member" })
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    private final String MESSAGE_200 = "성공";
    private final String MESSAGE_401 = "인증 실패";
    private final String MESSAGE_500 = "서버 오류";

    @PostMapping("/signup")
    @ApiOperation(value = "가입하기", notes = "회원가입 요청 API")
    @ApiResponses({ @ApiResponse(code = 200, message = MESSAGE_200, response = BaseResponse.class),
            @ApiResponse(code = 401, message = MESSAGE_401, response = BaseResponse.class),
            @ApiResponse(code = 500, message = MESSAGE_500, response = BaseResponse.class) })
    public ResponseEntity<? extends BaseResponse> signup(@RequestBody @Valid SignupRequest request) {

        memberService.signup(request);
        return ResponseEntity.status(200).body(BaseResponse.of(200, "Success"));
    }

}

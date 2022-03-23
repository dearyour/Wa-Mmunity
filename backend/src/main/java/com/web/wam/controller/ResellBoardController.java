package com.web.wam.controller;

import com.web.wam.model.dto.BaseResponse;
import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.dto.resellboard.ResellBoardPostRequest;
import com.web.wam.model.service.ResellBoardService;
import com.web.wam.model.service.S3Service;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "리셀게시판 API", tags = { "resellboard" })
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RequestMapping("/resellboard")
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
    public ResponseEntity<? extends BaseResponse> post(@RequestBody @ApiParam(value = "수정할 회원 사진 파일.", required = true) MultipartFile photo,
                                                         String title,
                                                         String content,
                                                         String tag,
                                                         String price,
                                                         String member_id) {

        ResellBoardPostRequest request = new ResellBoardPostRequest();
        request.setContent(content);
        request.setMemberId(Integer.parseInt(member_id));
        request.setTitle(title);
        request.setTag(tag);
        request.setPrice(Integer.parseInt(price));

        String photoPath = "";
        if(photo != null) {
            photoPath = s3Service.uploadToResellboard(photo);
        }
        resellBoardService.createArticle(request, photoPath);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(MSG, SUCCESS);
        return ResponseEntity.status(200).body(BaseResponse.of(200, resultMap));
    }
}

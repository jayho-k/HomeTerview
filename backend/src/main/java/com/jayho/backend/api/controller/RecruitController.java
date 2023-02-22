package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.api.service.RecruitService;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.BaseEntity;
import com.jayho.backend.db.entity.Recruit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recruits")
public class RecruitController {

    private final RecruitService recruitService;

    public ResponseEntity<? extends BaseResponseBody> register(Authentication authentication,
                                                               @RequestBody RecruitInfoReq recruitInfoReq){
        // 신청 => 가입 성공 => 모집글 생성
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Recruit recruit = recruitService.writeRecruit(recruitInfoReq);


    }









}

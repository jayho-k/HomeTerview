package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.api.service.ApplyService;
import com.jayho.backend.api.service.RecruitService;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.BaseEntity;
import com.jayho.backend.db.entity.JoinType;
import com.jayho.backend.db.entity.Recruit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recruits")
public class RecruitController {

    private final RecruitService recruitService;
    private final ApplyService applyService;

    @PostMapping()
    public ResponseEntity<? extends BaseResponseBody> register(Authentication authentication,
                                                               @RequestBody RecruitInfoReq recruitInfoReq){
        // 신청 => 가입 성공 => 모집글 생성
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Recruit recruit = recruitService.writeRecruit(recruitInfoReq);
        applyService.applyRecruit(userDetails.getUserNo(), recruit.getId(), JoinType.LEADER);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 작성에 성공하였습니다."));
    }









}

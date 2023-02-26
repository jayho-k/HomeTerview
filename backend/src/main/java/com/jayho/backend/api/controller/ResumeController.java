package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.ResumeInfoReq;
import com.jayho.backend.api.request.ResumeUpdateReq;
import com.jayho.backend.api.response.ResumeListRes;
import com.jayho.backend.api.service.ResumeService;
import com.jayho.backend.api.service.dto.ResumeListDto;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resume")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("")
    public ResponseEntity<? extends BaseResponseBody> createResume(Authentication authentication, @RequestBody ResumeInfoReq resumeInfoReq) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        resumeService.createResume(userDetails.getUserNo(), resumeInfoReq.getResumeTitle());
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서가 생성되었습니다."));
    }

    @GetMapping("")
    public ResponseEntity<ResumeListRes> getResumeList(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        List<ResumeListDto> resumeList = resumeService.getResumeList(userDetails.getUserNo());
        return ResponseEntity.status(200).body(ResumeListRes.of(resumeList, 200, "자기소개서 목록조회에 성공하였습니다."));
    }

    @PutMapping("")
    public ResponseEntity<? extends BaseResponseBody> updateResume(Authentication authentication,@RequestParam Long resumeId, @RequestBody ResumeInfoReq ResumeInfoReq) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        ResumeListDto resumeListDto = resumeService.updateResume(userDetails.getUserNo(), resumeId, ResumeInfoReq.getResumeTitle());
        if (resumeListDto==null){
            ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당 자기소개서가 없습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서 제목 수정에 성공했습니다."));
    }
}
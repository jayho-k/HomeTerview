package com.jayho.backend.api.controller;

import com.jayho.backend.api.response.StudyRes;
import com.jayho.backend.api.service.ResumeService;
import com.jayho.backend.api.service.StudyService;
import com.jayho.backend.api.service.dto.StudyCreateDto;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/study")
public class StudyController {

    private final StudyService studyService;
    private final ResumeService resumeService;

    @PostMapping()
    public ResponseEntity<? extends BaseResponseBody> createStudy(Authentication authentication, @RequestParam Long recruitId) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        StudyCreateDto studyCreateDto;
        try {
            studyCreateDto = studyService.createStudy(recruitId, userDetails.getUserNo());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 생성에 실패하였습니다."));
        }
        if (studyCreateDto==null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "leader가 아니어서 스터디 생성에 실패햐였습니다."));
        return ResponseEntity.status(200).body(StudyRes.of(studyCreateDto, 200, "스터디가 생성되었습니다."));
    }

}

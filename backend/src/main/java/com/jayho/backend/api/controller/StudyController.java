package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.CommonQuestionReq;
import com.jayho.backend.api.response.CommonQuestionListRes;
import com.jayho.backend.api.response.StudyDetailRes;
import com.jayho.backend.api.response.StudyListRes;
import com.jayho.backend.api.response.StudyRes;
import com.jayho.backend.api.service.CommonQuestionService;
import com.jayho.backend.api.service.ResumeService;
import com.jayho.backend.api.service.StudyService;
import com.jayho.backend.api.service.dto.CommonQuestionDto;
import com.jayho.backend.api.service.dto.CommonQuestionListDto;
import com.jayho.backend.api.service.dto.StudyCreateDto;
import com.jayho.backend.api.service.dto.StudyDetailGetDto;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.CommonQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/study")
public class StudyController {

    private final StudyService studyService;
    private final ResumeService resumeService;
    private final CommonQuestionService commonQuestionService;

    @PostMapping("")
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

    @GetMapping("")
    public ResponseEntity<StudyListRes> studyList(Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        List<StudyCreateDto> studyList = studyService.getStudyList(userDetails.getUserNo());
        return ResponseEntity.status(200).body(StudyListRes.of(studyList, 200, "스터디 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/{studyId}")
    public ResponseEntity<? extends BaseResponseBody> studyDetail(Authentication authentication, @PathVariable Long studyId) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        StudyDetailGetDto studyDetail;
        try {
            studyDetail = studyService.getStudyDetail(studyId, userDetails.getUserNo());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 상세 조회를 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(StudyDetailRes.of(studyDetail, 200, "스터디 상세 조회를 성공하였습니다."));
    }

    @PostMapping({"/{studyId}/common"})
    public ResponseEntity<? extends BaseResponseBody> registerCommonQuestion(Authentication authentication,
                                                                             @PathVariable("studyId") Long studyId,
                                                                             @RequestBody @Valid CommonQuestionReq commonQuestionReq) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        try {
            commonQuestionService.registerCommonQuestion(userDetails.getUserNo(), studyId, commonQuestionReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공통 질문 등록에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "공통 질문 등록이 완료되었습니다."));
    }


    @GetMapping({"/{studyId}/common"})
    public ResponseEntity<CommonQuestionListRes> getCommonQuestionList(@PathVariable Long studyId) {
        List<CommonQuestionListDto> commonQuestionDtoList = commonQuestionService.getList(studyId);
        return ResponseEntity.status(200).body(CommonQuestionListRes.of(commonQuestionDtoList, 200, "스터디 스페이스에 등록된 공통 질문 목록입니다."));
    }

    @PutMapping({"/resume/{studyId}/{resumeId}"})
    public ResponseEntity<? extends BaseResponseBody> updateRegistedResume(Authentication authentication,
                                                                           @PathVariable Long studyId,
                                                                           @PathVariable Long resumeId) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        try {
            studyService.updateRegistedResume(studyId, userDetails.getUserNo(), resumeId);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 등록에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서가 등록되었습니다."));
    }



}

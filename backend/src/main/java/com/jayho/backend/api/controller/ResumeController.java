package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.PersonalQuestionReq;
import com.jayho.backend.api.request.ResumeDetailCreateReq;
import com.jayho.backend.api.request.ResumeInfoReq;
import com.jayho.backend.api.request.UpdateResumeReq;
import com.jayho.backend.api.response.ResumeDetailRes;
import com.jayho.backend.api.response.ResumeListRes;
import com.jayho.backend.api.service.PersonalQuestionService;
import com.jayho.backend.api.service.ResumeService;
import com.jayho.backend.api.service.dto.ResumeDetailDto;
import com.jayho.backend.api.service.dto.ResumeListDto;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.ResumeDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resume")
public class ResumeController {

    private final ResumeService resumeService;
    private final PersonalQuestionService personalQuestionService;

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

    @DeleteMapping()
    public ResponseEntity<? extends BaseResponseBody> deleteResume(Authentication authentication, @RequestParam Long resumeId) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        int result = resumeService.deleteResume(resumeId, userDetails.getUserNo());
        if (result==0){
            return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당 자기소개서가 없습니다."));
        } else if(result ==1){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "본인의 자기소개서가 아닙니다."));
        } else {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서가 삭제되었습니다."));
        }
    }

    @PostMapping("/detail")
    public ResponseEntity<? extends BaseResponseBody> createResumeDetail(@RequestBody @Valid ResumeDetailCreateReq resumeDetailCreateReq) throws Exception {
        try {
            resumeService.createResumeDetail(resumeDetailCreateReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서가 등록되었습니다."));
    }


    @GetMapping("/detail")
    public ResponseEntity<? extends BaseResponseBody> detailResumeDetail(@RequestParam Long resumeId, @RequestParam Long itemNo) throws Exception {
        ResumeDetailDto resumeDetail = resumeService.getResumeDetail(resumeId, itemNo);
        if (resumeDetail == null)
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 상세조회에 실패하였습니다."));
        return ResponseEntity.status(200).body(ResumeDetailRes.of(resumeDetail, 200, "자기소개서 상세조회에 성공하였습니다."));
    }

    @PutMapping("/{resumeDetailId}")
    public ResponseEntity<? extends BaseResponseBody> updateResume(Authentication authentication, @PathVariable Long resumeDetailId, @RequestBody UpdateResumeReq updateResumeReq) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        ResumeDetail resumeDetail = resumeService.getResumeDetailById(resumeDetailId).orElse(null);
        if (resumeDetail == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 자기소개서 상세가 없습니다."));
        try {
            ResumeDetailDto updatedResumeDetail = resumeService.updateResumeDetail(resumeDetail, userDetails.getUserNo(), updateResumeReq);
            return ResponseEntity.status(200).body(ResumeDetailRes.of(updatedResumeDetail, 200, "자기소개서 상세내용이 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 상세내용 수정에 실패하였습니다."));
        }
    }

    @DeleteMapping("/{resumeDetailId}")
    public ResponseEntity<? extends BaseResponseBody> deleteResumeDetail(Authentication authentication,@PathVariable Long resumeDetailId) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        int result = resumeService.deleteResumeDetail(resumeDetailId, userDetails.getUserNo());
        if (result == 0) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서 상세 삭제가 완료되었습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 상세 항목 삭제를 실패하셨습니다."));
    }

    @PostMapping({"/study/{stdId}/detail/{resumeDetailId}/question"})
    public ResponseEntity<? extends BaseResponseBody> registerQuestion(Authentication authentication,
                                                                       @PathVariable("stdNo") Long stdNo,
                                                                       @PathVariable("detailNo") Long detailNo,
                                                                       @RequestBody @Valid PersonalQuestionReq personalQuestionReq) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        try {
            personalQuestionService.registerQuestion(userDetails.getUserNo(), stdNo, detailNo, personalQuestionReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "개인 질문 등록에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "개인 질문이 등록되었습니다."));
    }
}
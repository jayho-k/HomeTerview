package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.api.response.ApplyListRes;
import com.jayho.backend.api.response.RecruitListRes;
import com.jayho.backend.api.response.RecruitRes;
import com.jayho.backend.api.service.ApplyService;
import com.jayho.backend.api.service.RecruitService;
import com.jayho.backend.api.service.dto.ApplyRecruitDto;
import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.BaseEntity;
import com.jayho.backend.db.entity.JoinType;
import com.jayho.backend.db.entity.Recruit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Recruit recruit = recruitService.writeRecruit(recruitInfoReq, userDetails.getUserNo());
        applyService.applyRecruit(userDetails.getUserNo(), recruit.getId(), JoinType.LEADER);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 작성에 성공하였습니다."));
    }

    @GetMapping("/recruit")
    public ResponseEntity<RecruitListRes> getRecruitList(@RequestParam int type, Pageable pageable){
        Page<RecruitDto> recruitList = recruitService.getList(type, pageable);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruitList,200, "스터디 모집글 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/recruiting")
    public ResponseEntity<? extends RecruitListRes> getRecruitingList(@RequestParam int type, Pageable pageable) throws Exception{
        // 모집중 스터디 모집글 목록 조회
        // type => all, com, free 모집글을 뿌리면 된다.
        Page<RecruitDto> recruitingList = recruitService.getRecruitingList(type,pageable);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruitingList,200, "모집글 조회를 성공하였습니다."));
    }

    @GetMapping("/applying")
    public ResponseEntity<ApplyListRes> getApplingList(Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        List<Recruit> applyingList = recruitService.getApplyingList(userDetails.getUserNo());
        return ResponseEntity.status(200).body(ApplyListRes.of(applyingList, 200, "신청 중인 스터디 모집글 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/{recruitId}")
    public ResponseEntity<? extends RecruitRes> getRecruitDetail(@PathVariable Long recruitId) {
        RecruitDto recruitDto = recruitService.getRecruitDetail(recruitId);
        return ResponseEntity.status(200).body(RecruitRes.of(recruitDto, 200, "스터디 모집글 상세조회를 성공하였습니다."));

    }
    @PutMapping("/{recruitId}")
    public ResponseEntity<? extends BaseResponseBody> updateRecruitDetail(@PathVariable Long recruitId, @RequestBody RecruitInfoReq recruitInfoReq)  {
        RecruitDto updatedRecruit = recruitService.updateRecruitDetail(recruitId, recruitInfoReq);
        if (updatedRecruit == null)
        return ResponseEntity.status(403).body(BaseResponseBody.of(403, "해당하는 스터디 모집글이 없습니다."));
        return ResponseEntity.status(200).body(RecruitRes.of(updatedRecruit,200, "스터디 모집글이 수정되었습니다."));
    }

    @DeleteMapping("/{recruitId}")
    public ResponseEntity<? extends BaseResponseBody> deleteRecruit(@PathVariable Long recruitId) throws Exception {
        int result = recruitService.deleteRecruit(recruitId);
        if (result==0){
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 삭제가 완료되었습니다."));
        }else{
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집글 삭제에 실패하였습니다."));
        }
    }


}

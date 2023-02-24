package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.api.response.ApplyListRes;
import com.jayho.backend.api.response.RecruitListRes;
import com.jayho.backend.api.service.ApplyService;
import com.jayho.backend.api.service.RecruitService;
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
        Recruit recruit = recruitService.writeRecruit(recruitInfoReq);
        applyService.applyRecruit(userDetails.getUserNo(), recruit.getId(), JoinType.LEADER);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 작성에 성공하였습니다."));
    }

    // 이것들 => page사용으로 바꿔야함
    @GetMapping("/recruit")
    public ResponseEntity<RecruitListRes> getRecruitList(Pageable pageable){
        Page<Recruit> recruitList = recruitService.getList(pageable);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruitList, 200, "스터디 모집글 목록 조회를 성공하였습니다."));
    }

    // 이것들 => page사용으로 바꿔야함
    @GetMapping("/recruiting")
    public ResponseEntity<? extends RecruitListRes> getRecruitingList(@RequestParam int type, Pageable pageable){
        // 모집중 스터디 모집글 목록 조회
        // type => all, com, free 모집글을 뿌리면 된다.
        Page<Recruit> recruitingList = recruitService.getRecruitingList(type,pageable);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruitingList,200, "모집글 조회를 성공하였습니다."));
    }

    @GetMapping("/applying")
    public ResponseEntity<ApplyListRes> getApplingList(Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        List<Recruit> applyingList = recruitService.getApplyingList(userDetails.getUserNo());
        return ResponseEntity.status(200).body(ApplyListRes.of(applyingList, 200, "신청 중인 스터디 모집글 목록 조회를 성공하였습니다."));
    }

}

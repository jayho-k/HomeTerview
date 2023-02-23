package com.jayho.backend.api.controller;

import com.jayho.backend.api.service.ApplyService;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.JoinType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apply")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/{recruitNo}")
    public ResponseEntity<? extends BaseResponseBody> applyRecruit(Authentication authentication,
                                                                   @PathVariable Long recruitId) {

        UserDetails userDetails = (UserDetails) authentication.getDetails();
        int result = applyService.applyRecruit(userDetails.getUserNo(), recruitId, JoinType.NORMAL);
        if (result == 0) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집 신청에 실패하였습니다."));
        else if (result == 1) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "스터디 모집 정원을 초과하였습니다."));
        else return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집 신청이 완료되었습니다."));
    }


}

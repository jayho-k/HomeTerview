package com.jayho.backend.api.service;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.db.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitService {
    Recruit writeRecruit(RecruitInfoReq recruitInfoReq,Long userId);
    Page<RecruitDto> getList(int type, Pageable pageable);
    Page<RecruitDto> getRecruitingList(int type, Pageable pageable);
    RecruitDto getRecruitDetail(Long recruitId);
    RecruitDto updateRecruitDetail(Long recruitId, RecruitInfoReq recruitInfoReq);
    int deleteRecruit(Long recruitId);
    List<Recruit> getApplyingList(Long userId);


}

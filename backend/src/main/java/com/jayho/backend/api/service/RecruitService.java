package com.jayho.backend.api.service;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.db.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitService {
    Recruit writeRecruit(RecruitInfoReq recruitInfoReq);
    Page<Recruit> getList(Pageable pageable);
    Page<Recruit> getRecruitingList(int type, Pageable pageable);

    List<Recruit> getApplyingList(Long userId);
}

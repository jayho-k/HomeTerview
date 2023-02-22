package com.jayho.backend.api.service;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.db.entity.Recruit;

public interface RecruitService {
    Recruit writeRecruit(RecruitInfoReq recruitInfoReq);
}
